package com.example.emp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class User {
	
	@Id
	@GeneratedValue
	private int id;
	
	@NotEmpty(message = "name is required.")
	@Size(min = 2, max = 100, message = "The length of first name must be between 2 and 100 characters.")
	private String firstname;
	
	@NotEmpty(message = "name is required.")
	@Size(min = 2, max = 100, message = "The length of last name must be between 2 and 100 characters.")
	private String lastname;
	
	@NotEmpty(message = "username is required.")
	private String username;
	
	@NotEmpty(message = "User Type is required.")
	private String usertype;
	
	@Email(message = "Email is not valid", regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
	@NotEmpty(message = "Email cannot be empty")
	private String email;
	
	@NotEmpty(message = "Mobile Number is required.")
	@NotNull
	@Size(min = 10, max = 10, message = "The length of Mobile Number must be 10 characters.")
	private String contact;
	

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", usertype=" + usertype
				+ ", email=" + email + ", contact=" + contact + "]";
	}

	
}