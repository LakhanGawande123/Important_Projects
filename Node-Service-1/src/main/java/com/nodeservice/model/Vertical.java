package com.nodeservice.model;


public class Vertical {
	
	private String verticalname;
	private String verticalid;
	
	
	public String getVerticalname() {
		return verticalname;
	}
	public void setVerticalname(String verticalname) {
		this.verticalname = verticalname;
	}
	public String getVerticalid() {
		return verticalid;
	}
	public void setVerticalid(String verticalid) {
		this.verticalid = verticalid;
	}
	@Override
	public String toString() {
		return "Vertical [verticalname=" + verticalname + ", verticalid=" + verticalid + "]";
	}
	
	

}
