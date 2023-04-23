package com.nodeservice.model;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
//import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


//import jakarta.persistence.Entity;
//import jakarta.persistence.EnumType;
//import jakarta.persistence.Enumerated;
//import jakarta.persistence.GeneratedValue;
//import jakarta.persistence.GenerationType;
//import jakarta.persistence.Id;
//import jakarta.persistence.Temporal;
//import jakarta.persistence.TemporalType;
//import jakarta.transaction.Status;
import lombok.Data;

@Entity
@Data
public class Node {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	private String nodeType;
	
	@Column(unique = true)
	private String email;
	private String password;
	private String token;
	
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date time;
	
	@Enumerated(EnumType.STRING)
    private Status status;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Node [id=" + id + ", nodeType=" + nodeType + ", email=" + email + ", password=" + password + ", token="
				+ token + ", time=" + time + ", status=" + status + "]";
	}
	
}
