package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.TwilioSmsSender;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;
    
    @Autowired
    private TwilioSmsSender smsService;
    
    @Autowired
	private UserRepository userRepository;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @PostMapping("/register1")
    public ResponseEntity<?> registerUser1(@RequestBody User user) {
        return userService.saveUserDemo(user);
    }

    @RequestMapping(value="/confirm-account", method= {RequestMethod.GET, RequestMethod.POST})
    public ResponseEntity<?> confirmUserAccount(@RequestParam("token")String confirmationToken) {
        return userService.confirmEmail(confirmationToken);
    }

    
    @PostMapping("/users/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        List<User> users = userRepository.findAll();

        for (User other : users) {
            if (other.equals(user)) {
                user.setEnabled(true);
                userRepository.save(user);
                return ResponseEntity.ok("User Login successfully...!");
            }
        }

        return ResponseEntity.ok("Login Failed");
    }
    
    @GetMapping("/users/login")
	public ResponseEntity<User> userLogin(@RequestParam String userName, @RequestParam String userPassword) throws UserNotFoundException{
		User user = userService.getLoginData(userName, userPassword);
		return ResponseEntity.ok(user);
	}
    
    @PostMapping("/sms")
    public void sendSms( @RequestBody User user) {
        smsService.sendSms(user);
    }
    
    
    
    @PutMapping("/reset-password")
	public ResponseEntity<?> updatepassword(@RequestBody User user, @RequestParam String email) throws UserNotFoundException {
		return userService.resetPassword(email, user);
	}
}
