package com.jonathan.todo.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(AppException.class)
	public ResponseEntity<ApiError> handleAppExceptions(AppException ex){
		ApiError error = new ApiError(ex.getStatus().value(), ex.getMessage());
		return new ResponseEntity<>(error, ex.getStatus());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex) {
	    String errors = ex.getBindingResult().getFieldErrors()
	                      .stream()
	                      .map(fieldError -> fieldError.getDefaultMessage())
	                      .collect(Collectors.joining(", "));
	    ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(), errors);
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleUnexpectedExceptions(Exception ex) {
	    ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),
	                                  "An unexpected error occurred");
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
