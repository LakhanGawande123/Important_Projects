package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	// Get all users from db
	@Query("SELECT u FROM User u WHERE u.username = :username1 AND u.fname=:fname1 AND u.lname=:lname1 AND u.age=:age1")
	public List<User> findByUsernameAndFnameAndLnameAndAge(@Param("username1") String username,
			@Param("fname1") String fname, @Param("lname1") String lname, @Param("age1") String age);

	// Get Single user from db
	@Query("SELECT u FROM User u WHERE u.username = :username1 AND u.fname=:fname1 AND u.lname=:lname1 AND u.age=:age1")
	public User findByUsernameAndFnameAndLnameAndAge1(@Param("username1") String username,
			@Param("fname1") String fname, @Param("lname1") String lname, @Param("age1") String age);

	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query("UPDATE User SET username = :username WHERE id = :id AND fname = :fname")
	Integer updateUsernameByIdAndFname(String username, int id, String fname);


}