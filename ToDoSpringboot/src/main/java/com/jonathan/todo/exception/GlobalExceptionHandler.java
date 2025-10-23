package com.jonathan.todo.exception;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.jonathan.todo.util.MessageUtil;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	private final MessageUtil messageUtil;
	
	public GlobalExceptionHandler(MessageUtil messageUtil) {
		this.messageUtil = messageUtil;
	}

	@ExceptionHandler(AppException.class)
	public ResponseEntity<ApiError> handleAppExceptions(AppException ex, HttpServletRequest request){
		ApiError error = new ApiError(ex.getStatus().value(),ex.getStatus().getReasonPhrase(), ex.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(error, ex.getStatus());
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ApiError> handleValidationExceptions(MethodArgumentNotValidException ex, HttpServletRequest request) {
	    String errorMessages = ex.getBindingResult().getFieldErrors()
	                      .stream()
	                      .map(fieldError -> fieldError.getDefaultMessage())
	                      .collect(Collectors.joining(", "));
	    ApiError error = new ApiError(HttpStatus.BAD_REQUEST.value(),HttpStatus.BAD_REQUEST.getReasonPhrase(), errorMessages, request.getRequestURI());
	    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ApiError> handleUnexpectedExceptions(Exception ex, HttpServletRequest request) {
		String errorMessage = messageUtil.getMessage("error.unexpected");
	    ApiError error = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR.value(),HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase(),
	    		errorMessage,request.getRequestURI());
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
