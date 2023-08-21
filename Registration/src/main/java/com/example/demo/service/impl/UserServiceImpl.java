package com.example.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.config.TwilioConfiguration;
import com.example.demo.config.TwilioSmsSender;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.ConfirmationToken;
import com.example.demo.model.User;
import com.example.demo.repository.ConfirmationTokenRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.rest.api.v2010.account.MessageCreator;
import com.twilio.type.PhoneNumber;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	EmailService emailService;
	
    PasswordEncoder passwordEncoder;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TwilioSmsSender.class);

    private final TwilioConfiguration twilioConfiguration;

    public UserServiceImpl(UserRepository userRepository, TwilioConfiguration twilioConfiguration) {
    	this.userRepository=userRepository;
    	this.passwordEncoder = new BCryptPasswordEncoder();
    	this.twilioConfiguration = twilioConfiguration;
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
	
	
	/* 
		User register at a time send successful registration on email and sms (Mobile) 
	*/ 
	@Override
	public ResponseEntity<?> saveUserDemo(User user) {

		if (userRepository.existsByUserEmail(user.getUserEmail())) {
			return ResponseEntity.badRequest().body("Error: Email is already in use!");
		}

		String encodedPassword = this.passwordEncoder.encode(user.getUserPassword());
		user.setUserPassword(encodedPassword);
		userRepository.save(user);
		
		//email sending code
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setTo(user.getUserEmail());
		mailMessage.setSubject("Complete Registration!");
		//String encoded = new BCryptPasswordEncoder().encode(user.getUserPassword());
		mailMessage.setText("User Name: "+ user.getUserName()+",  Password: "+user.getUserPassword());
		emailService.sendEmail(mailMessage);
		
		//sms sending code
		if (isPhoneNumberValid(user.getMobnumber())) {
            PhoneNumber to = new PhoneNumber(user.getMobnumber());
            PhoneNumber from = new PhoneNumber(twilioConfiguration.getTrialNumber());
            String message = "User Registration Successful "+" "+"Username:"+user.getUserName()+", Password"+user.getUserPassword();
            MessageCreator creator = Message.creator(to, from, message);
            creator.create();
            LOGGER.info("Send sms {}", user);
        } else {
            throw new IllegalArgumentException(
                    "Phone number [" + user.getMobnumber() + "] is not a valid number"
            );
        }
		
		return ResponseEntity.ok(">>>>>>>> Success <<<<<<<<<");
	}
	
	private boolean isPhoneNumberValid(String mobnumber) {
        // TODO: Implement phone number validator
        return true;
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
