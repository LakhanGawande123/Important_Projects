package com.example.emp.exception;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

	  @ExceptionHandler(EmployeeNotFoundException.class)
	  public ResponseEntity < ErrorDetails > userNotFoundException(EmployeeNotFoundException ex) {

	    ErrorDetails errorModel = new ErrorDetails(0, ex.getMessage());

	    return new ResponseEntity < ErrorDetails > (errorModel, HttpStatus.NOT_FOUND);

	  }

	  @ExceptionHandler(Exception.class)
	  public ResponseEntity < ? > globleExcpetionHandler(Exception ex) {

	    ErrorDetails errorModel = new ErrorDetails(0, ex.getMessage());

	    return new ResponseEntity < > (errorModel, HttpStatus.INTERNAL_SERVER_ERROR);

	  }
	  
	  
		/*
		 * @Override protected ResponseEntity<Object>
		 * handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders
		 * headers, HttpStatus status, WebRequest request) {
		 * 
		 * Map<String, List<String>> body = new HashMap<>();
		 * 
		 * List<String> errors = ex.getBindingResult().getFieldErrors().stream()
		 * .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.
		 * toList());
		 * 
		 * body.put("errors", errors);
		 * 
		 * return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST); }
		 */
	  
		
	  @Override
		protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
				HttpHeaders headers, HttpStatus status, WebRequest request) {
			Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", new Date());
	        body.put("status", status.value());
	 
	        //Get all errors
	        List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(x -> x.getDefaultMessage())
	                .collect(Collectors.toList());

	        body.put("errors", errors);

	        return new ResponseEntity<>(body, status);
		}
	}

