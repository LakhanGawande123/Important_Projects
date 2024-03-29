package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@RestController
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/msg")
	public String message() {
		return "Hello Demo.....!";
	}

	@PostMapping("/saveUser/{username}")
	public ResponseEntity<User> saveUser(@PathVariable("username") String username, @RequestBody User user) {

		User user1 = userRepository.save(user);

		return new ResponseEntity<User>(user1, HttpStatus.CREATED);

	}

	@GetMapping("/getUsers/{username}/{fname}/{lname}/{age}")
	public ResponseEntity<List<User>> getAllUser(@PathVariable("username") String username,
			@PathVariable("fname") String fname, @PathVariable("lname") String lname, @PathVariable("age") String age) {

		List<User> user1 = userRepository.findByUsernameAndFnameAndLnameAndAge(username, fname, lname, age);

		return new ResponseEntity<List<User>>(user1, HttpStatus.OK);

	}

	@GetMapping("/getUserInfo/{username}/{fname}/{lname}/{age}")
	public ResponseEntity<User> getUserInfo(@PathVariable("username") String username,
			@PathVariable("fname") String fname, @PathVariable("lname") String lname, @PathVariable("age") String age) {

		User user1 = userRepository.findByUsernameAndFnameAndLnameAndAge1(username, fname, lname, age);

		return new ResponseEntity<User>(user1, HttpStatus.OK);

	}

	@PutMapping("/user/update/{username}/{id}/{fname}")
	public ResponseEntity<?> updateUsernamebyId(@PathVariable String username, @PathVariable int id,
			@PathVariable String fname) {

		Integer user1 = userRepository.updateUsernameByIdAndFname(username, id, fname);
		return new ResponseEntity<String>(user1 + " record(s) updated.", HttpStatus.OK);
	}

	@GetMapping("/getAllUser")
	public ResponseEntity<List<User>> getAllUser() {

		List<User> user1 = userRepository.findAll();

		return new ResponseEntity<List<User>>(user1, HttpStatus.OK);

	}

}
