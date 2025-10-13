package com.jonathan.todo.dto.request;

import com.jonathan.todo.validation.OnLogin;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class LoginRequest {
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 50, groups = OnLogin.class, message = "Invalid credentials")
	private String username;
	@NotBlank(message = "Password is required")
	@Size(min = 8, max = 100)
	private String password;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
