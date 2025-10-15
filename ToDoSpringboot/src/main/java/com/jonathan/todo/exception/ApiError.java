package com.jonathan.todo.exception;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ "timestamp", "status","error", "message" })
public class ApiError {
	
	private final LocalDateTime timestamp = LocalDateTime.now();
	private final int status;
	private final String error;
	private final String message;
	private final String path;
	
	public ApiError(int status, String error, String message, String path) {
		this.status = status;
		this.error = error;
		this.message = message;
		this.path = path;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	public int getStatus() {
		return status;
	}
	
	public String getError() {
		return error;
	}
	
	public String getMessage() {
		return message;
	}

	public String getPath() {
		return path;
	}
	
}
