package com.jonathan.todo.exception;

import org.springframework.http.HttpStatus;

public class UserAlreadyExistsException extends AppException{

	public UserAlreadyExistsException(String message) {
		super(message, HttpStatus.CONFLICT);
	}
	
}
