package com.example.emp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

import com.example.emp.model.User;
import com.example.emp.repo.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
    private EmailService emailService;
	
	public ResponseEntity<User> saveUser(User user) {
		User user1 = userRepository.save(user);
		//return user1;
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	public User saveUser1(User user) {
		User user1 = userRepository.save(user);
		//return user1;
		return user1;
	}
	
	public ResponseEntity<User> getUser(int id) {
		User user1 = userRepository.findById(id).get();
		//return user1;
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	
//	public ResponseEntity<?> saveUser3(User user) {
//
//        if (userRepository.existsByEmail(user.getEmail())) {
//            return ResponseEntity.badRequest().body("Error: Email is already in use!");
//        }
//
//        User response = userRepository.save(user);
//        
//        if (userRepository.existsByEmail(null)) {
//            
//			SimpleMailMessage msg = new SimpleMailMessage();
//			//ResponseEntity<User> result = userService.getUser(user.getId());
//			//System.out.println(">>>> Result "+ result);
//	        msg.setTo(user.getEmail());
//
//	        msg.setSubject("Username Testing from Spring Boot");
//	        msg.setText("To confirm your account, Your Username : "+user.getUsername());
//	       // ResponseEntity<User>  = userService.saveUser(response);
//	        
//	        //return new ResponseEntity(result, HttpStatus.OK);
//        }
//		return new ResponseEntity(response, HttpStatus.OK);
//        
//        
//        SimpleMailMessage mailMessage = new SimpleMailMessage();
//        mailMessage.setTo(user.getEmail());
//        mailMessage.setSubject("Complete Registration!");
//        mailMessage.setText("To confirm your account, please click here : "
//                +user.getUsername());
//        emailService.sendEmail(mailMessage);
//
//        return ResponseEntity.ok("Verify email by the link sent on your email address");
//    }

}

