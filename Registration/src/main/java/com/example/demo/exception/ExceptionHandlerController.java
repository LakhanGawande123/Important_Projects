package com.example.demo.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandlerController {
	
	@ExceptionHandler(UserNotFoundException.class)
	public @ResponseBody ExceptionResponse handlingException(UserNotFoundException exception, HttpServletRequest request){
		ExceptionResponse exceptionResponse = new ExceptionResponse();
		exceptionResponse.setMsg(exception.getMessage());
		exceptionResponse.setUrl(request.getRequestURI());
		return exceptionResponse;
	}

}
