package com.example.demo.exception;

public class ExceptionResponse {
	
	private String msg;
	private String url;
	
	public ExceptionResponse() {
		
	}
	
	public ExceptionResponse(String msg, String url) {
		super();
		this.msg = msg;
		this.url = url;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	

}
