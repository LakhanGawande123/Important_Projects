package com.example.demo.user.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.demo.user.model.Post;
import com.example.demo.user.model.User;

@RestController
@RequestMapping("/user1")
public class UserController {

	@Autowired
	private RestTemplate restTemplate;

	
	@GetMapping("/{userId}")
	public User getUser(@PathVariable("userId") String userId) {

		User userOne = new User(userId, "User Name" + userId, "xxxxxx" + userId);

		Post post1 = restTemplate.getForObject("http://localhost:9002/post1/1", Post.class);
		userOne.setPost(post1);

		return userOne;

	}
	 

}
