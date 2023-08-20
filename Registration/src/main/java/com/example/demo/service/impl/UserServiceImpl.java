package com.example.demo.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ConfirmationToken;
import com.example.demo.model.User;
import com.example.demo.repository.ConfirmationTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	EmailService emailService;
	
	//@Autowired
    PasswordEncoder passwordEncoder;
    
    
    public UserServiceImpl(UserRepository userRepository) {
    	this.userRepository=userRepository;
    	this.passwordEncoder = new BCryptPasswordEncoder();
    }

	@Override
	public ResponseEntity<?> saveUser(User user) {

		if (userRepository.existsByUserEmail(user.getUserEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}
		//user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
		String encodedPassword = this.passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encodedPassword);
		userRepository.save(user);

		ConfirmationToken confirmationToken = new ConfirmationToken(user);

		confirmationTokenRepository.save(confirmationToken);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getUserEmail());
		mailMessage.setSubject("Complete Registration!");
		mailMessage.setText("User Name: "+ user.getUserName()+",  Password: "+user.getUserPassword()+",  To confirm your account, please click here : "
				+ "http://localhost:9001/confirm-account?token=" + confirmationToken.getConfirmationToken());
		emailService.sendEmail(mailMessage);

		System.out.println("Confirmation Token: " + confirmationToken.getConfirmationToken());

		return ResponseEntity.ok("Verify email by the link sent on your email address");
	}
	

	@Override
	public ResponseEntity<?> confirmEmail(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			User user = userRepository.findByUserEmailIgnoreCase(token.getUserEntity().getUserEmail());
			user.setEnabled(true);
			userRepository.save(user);
			return ResponseEntity.ok("Email verified successfully!");
		}
		return ResponseEntity.badRequest().body("Error: Couldn't verify email");
	}
	
	@Override
	public ResponseEntity<?> saveUserDemo(User user) {

		if (userRepository.existsByUserEmail(user.getUserEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}

		String encodedPassword = this.passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encodedPassword);
		userRepository.save(user);

		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getUserEmail());
		mailMessage.setSubject("Complete Registration!");
		
		//String encoded = new BCryptPasswordEncoder().encode(user.getUserPassword());
		mailMessage.setText("User Name: "+ user.getUserName()+",  Password: "+user.getUserPassword());
		emailService.sendEmail(mailMessage);


		return ResponseEntity.ok(">>>>>>>> Success <<<<<<<<<");
	}
	
	@Override
	public User getLoginData(String userName, String userPassword) throws UserNotFoundException {
		System.out.println("Login Credential "+userName+","+userPassword);
		User login = userRepository.findByUserNameAndUserPassword(userName, userPassword);
		if(login != null && login.isEnabled()) {
			return login;
		}else {
			System.out.println("Invalid Username and Password...!");
			throw new UserNotFoundException("Invalid Username and Password...!");
		}
	}
}
