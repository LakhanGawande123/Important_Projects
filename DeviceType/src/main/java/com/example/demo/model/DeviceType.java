package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DeviceType {
	
	@Id
	String id;
	String name;

}
