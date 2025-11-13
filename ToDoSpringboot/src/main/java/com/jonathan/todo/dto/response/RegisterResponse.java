package com.jonathan.todo.dto.response;

import java.time.Instant;

public class RegisterResponse {
    private final Long userId;
    private final String username;
    private final Instant createdAt;

	public RegisterResponse(Long userId, String username,Instant createdAt){
		this.userId = userId;
		this.username = username;
		this.createdAt = createdAt;
	}
	
	public Long getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}
	public Instant getCreatedAt() {
		return createdAt;
	}

}
