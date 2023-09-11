package com.example.demo.service;

import org.springframework.http.ResponseEntity;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;

public interface UserService {
	
	ResponseEntity<?> saveUser(User user);

    ResponseEntity<?> confirmEmail(String confirmationToken);
    
    public ResponseEntity<?> saveUserDemo(User user);
    
    public User getLoginData(String userName, String userPassword)throws UserNotFoundException;
    
    public ResponseEntity<?> resetPassword(String email, User user) throws UserNotFoundException;
    
   

}
