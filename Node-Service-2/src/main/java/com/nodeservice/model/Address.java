package com.nodeservice.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor 
@AllArgsConstructor
public class Address implements Serializable {
	
    private final static long serialVersionUID = 7702L;
    
    private int doorNumber;
    private String roadName;
    private String postalCode;
    
    
	public int getDoorNumber() {
		return doorNumber;
	}
	public void setDoorNumber(int doorNumber) {
		this.doorNumber = doorNumber;
	}
	public String getRoadName() {
		return roadName;
	}
	public void setRoadName(String roadName) {
		this.roadName = roadName;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
    
    

}
