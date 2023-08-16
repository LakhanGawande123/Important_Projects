package com.example.demo.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "A video with this name already exists")
public class VideoAlreadyExistsException extends RuntimeException {

}
