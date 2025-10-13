package com.jonathan.todo.dto.response;

import java.time.LocalDateTime;

public class RegisterResponse {
    private final Integer userId;
    private final String username;
    private final LocalDateTime createdAt;
    private final String message;

	public RegisterResponse(Integer userId, String username,LocalDateTime createdAt, String message){
		this.userId = userId;
		this.username = username;
		this.createdAt = createdAt;
		this.message = message;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public String getMessage() {
		return message;
	}

}
