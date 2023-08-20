package com.example.emp.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.emp.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	 User findByEmail(String email);
	 
	 Boolean existsByEmail(String email);

}
