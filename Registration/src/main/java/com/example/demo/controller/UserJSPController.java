package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.config.TwilioSmsSender;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@Controller
public class UserJSPController {

	@Autowired
    private UserService userService;
    
    @Autowired
    private TwilioSmsSender smsService;
    
    @Autowired
	private UserRepository userRepository;
    
    @PostMapping("/register2")
    public ResponseEntity<?> registerUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    
    @GetMapping("/user1/login")
	public String userLogin(@RequestParam String userName, @RequestParam String userPassword, Model m, User user) throws UserNotFoundException{
		User user1 = userService.getLoginData(userName, userPassword);
		m.addAttribute("user", user1);
		
		
		return "index1";
	}
    
    @GetMapping("/index")
   	public String welcome() {
   		
   		return "index";
   	}
    
    
//    @RequestMapping(value = "/log")
//	public String getLoginCredentials(@RequestParam String un, @RequestParam String ps, Model m, User user) {
//		System.out.println("Login Data : " + un + "  " + ps);
//		List<User> ulist = us.getLoginData(un, ps);
//		m.addAttribute("userlist", ulist);
//
//		if(!ulist.isEmpty()) {
//	        return "success";
//	        }else {
//	        	String str1 = "You are not active User please contact Your Admin...!";
//	        	m.addAttribute("m", str1);
//	        	return "login";
//	        }
//	}
    
}
