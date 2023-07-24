package com.example.demo.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
  
    @Column(name = "name")
    private String name;
  
    @Column(name = "email")
    private String email;
  
    @Column(name = "age")
    private String age;
    
    private String addressid;
    
    public String getAddressid() {
		return addressid;
	}

	public void setAddressid(String addressid) {
		this.addressid = addressid;
	}

	public int getId() {
        return id;
    }
  
    public void setId(int id) {
        this.id = id;
    }
  
    public String getName() {
        return name;
    }
  
    public void setName(String name) {
        this.name = name;
    }
  
    public String getEmail() {
        return email;
    }
  
    public void setEmail(String email) {
        this.email = email;
    }
  
    public String getAge() {
        return age;
    }
  
    public void setAge(String age) {
        this.age = age;
    }

}