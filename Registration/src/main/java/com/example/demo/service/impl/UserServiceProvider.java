package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Service
@Transactional
public class UserServiceProvider {

	private final UserRepository userRepository;

	public UserServiceProvider(UserRepository userRepository) {
		this.userRepository=userRepository;
	}

	public void saveMyUser(User user) {
		userRepository.save(user);
	}

	public List<User> showAllUsers() {
		List<User> users = new ArrayList<User>();
		for (User user : userRepository.findAll()) {
			users.add(user);
		}

		return users;
	}

	public void deleteMyUser(long userid) {
		userRepository.deleteById(userid);
	}

	public User editUser(long userid) {
		return userRepository.findById(userid).get();
	}

	public User findByUsernameAndPassword(String userName, String userPassword) {
		return userRepository.findByUserNameAndUserPassword(userName, userPassword);
	}

}
