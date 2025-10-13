package com.jonathan.todo.dto.response;


public class LoginResponse {
	private final Integer userId;
	private final String username;
	
	public LoginResponse(Integer userId, String username) {
		this.userId = userId;
		this.username = username;
	}
	
	public Integer getUserId() {
		return userId;
	}
	public String getUsername() {
		return username;
	}

}
