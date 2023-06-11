package com.nodeservice.model;


import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
//import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

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
//@JsonFilter("NodeFilter")
//@JsonFilter("dynamicfilter")
public class Node {
	
	@Id
	@GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID id;
	
	@Column(nullable = false, length = 50, unique = true)
	@NotBlank(message = "Username cannot be blank")
	private String username;
	
	@Column(nullable = false, length = 50)
	@NotBlank(message = "NodeType cannot be blank")
	private String nodeType;
	
	@Column(nullable = false, length = 10, unique = true)
	private String phone;
	
	@Column(nullable = false, length = 50)
	private String district;
	
	@Column(nullable = false, length = 50)
	private String pincode;
	
	@Column(nullable = false, length = 50)
	private String city;
	
	@Column(nullable = false, length = 50)
	private String state;
	
	
	
	@Column(unique = true)
	private String email;
	
	private String password;
	
	private String token;
	
	@Transient
	//@JsonIgnore 

	private String realm;
	
	
	
	@Temporal(value=TemporalType.TIMESTAMP)
    private Date time;
	
	@Enumerated(EnumType.STRING)
    private Status status;
	
	//@Type(JsonType.class)
    //@Column(columnDefinition = "JSON")
	//@JsonDeserialize(using = VericalDeserializer.class)
	//private Vertical properties;

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNodeType() {
		return nodeType;
	}

	public void setNodeType(String nodeType) {
		this.nodeType = nodeType;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

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

	public String getRealm() {
		return realm;
	}

	public void setRealm(String realm) {
		this.realm = realm;
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
		return "Node [id=" + id + ", username=" + username + ", nodeType=" + nodeType + ", phone=" + phone
				+ ", district=" + district + ", pincode=" + pincode + ", city=" + city + ", state=" + state + ", email="
				+ email + ", password=" + password + ", token=" + token + ", realm=" + realm + ", time=" + time
				+ ", status=" + status + "]";
	}
	
}
