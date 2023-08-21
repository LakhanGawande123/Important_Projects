package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="userid")
	private Long userid;
	
	@Column(name="userName")
	private String userName;

	private String userEmail;

	private String userPassword;
	
	private String mobnumber;

	private boolean isEnabled;

	public User() {
    }

    public User(Long userid, String userName, String userEmail, String userPassword, String mobnumber, boolean isEnabled) {
        this.userid = userid;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.mobnumber = mobnumber;
        this.isEnabled = isEnabled;
    }

    public Long getUserId() {
        return userid;
    }

    public void setUserId(Long userId) {
        this.userid = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public User(String userName, String userEmail, String userPassword, String mobnumber, boolean isEnabled) {
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.mobnumber = mobnumber;
        this.isEnabled = isEnabled;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean enabled) {
        isEnabled = enabled;
    }

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getMobnumber() {
		return mobnumber;
	}

	public void setMobnumber(String mobnumber) {
		this.mobnumber = mobnumber;
	}
    
    
}
