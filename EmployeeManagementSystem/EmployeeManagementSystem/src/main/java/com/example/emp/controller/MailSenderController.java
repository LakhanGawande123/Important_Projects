package com.example.emp.controller;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.emp.model.User;
import com.example.emp.service.UserService;

@RestController
public class MailSenderController {
	
	@Autowired
    private JavaMailSender javaMailSender;
	
	@Autowired
    private UserService userService;
	
	@PostMapping(value = "/send")
	public String sendMail() throws MessagingException {
		
		System.out.println("---------------------------");

		 System.out.println("Mail Sending Start");
		    SimpleMailMessage msg = new SimpleMailMessage();
	        msg.setTo("lakhangawande92@gmail.com");

	        msg.setSubject("Testing from Spring Boot");
	        msg.setText("Hi Lakhan this is mail sendind trial........!");

	        javaMailSender.send(msg);
			System.out.println("Mail Sending Ends");
			System.out.println("---------------------------");
			
			return "Mail Sended.....successfully";
	}
	
	@PostMapping("/saveuser")
	//@RequestMapping(value="/saveuser", method= {RequestMethod.POST, RequestMethod.GET, RequestMethod.POST})
	//@RequestMapping(value="/saveuser", method= {RequestMethod.POST, RequestMethod.GET})
	public ResponseEntity<String> save(@RequestBody User user){
		ResponseEntity<User> response = userService.saveUser(user);
		System.out.println(":Lakhan"+ response);
		
		if (response.getStatusCode() == HttpStatus.CREATED) {
        
			SimpleMailMessage msg = new SimpleMailMessage();
			//ResponseEntity<User> result = userService.getUser(user.getId());
			//System.out.println(">>>> Result "+ result);
	        msg.setTo(user.getEmail());

	        msg.setSubject("Username Testing from Spring Boot");
	        msg.setText("To confirm your account, Your Username : "+user.getUsername());
	       // ResponseEntity<User>  = userService.saveUser(response);
	        
	        //return new ResponseEntity(result, HttpStatus.OK);
        }
		return new ResponseEntity(response, HttpStatus.OK);
		 
	}
	
	
//	@PostMapping("/saveuser")
//	public ResponseEntity<String> save1(@RequestBody User user){
//		User response = userService.saveUser1(user);
//		System.out.println(":Lakhan"+ response);
//		
//		//ResponseEntity<User> response1 = response
//		
//		if (response.getStatusCode() == HttpStatus.CREATED) {
//        
//			SimpleMailMessage msg = new SimpleMailMessage();
//			//ResponseEntity<User> result = userService.getUser(user.getId());
//			//System.out.println(">>>> Result "+ result);
//	        msg.setTo(response.getEmail());
//
//	        msg.setSubject("Username Testing from Spring Boot");
//	        msg.setText("To confirm your account, Your Username : "+user.getUsername());
//	       // ResponseEntity<User>  = userService.saveUser(response);
//	        
//	        //return new ResponseEntity(result, HttpStatus.OK);
//        }
//		return new ResponseEntity(response, HttpStatus.OK);
//	}
	
	
	@PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }

}