package com.jonathan.todo.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


public class RegisterRequest {
	@NotBlank(message = "Username is required")
	@Size(min = 3, max = 20, message = "Username must be between 3 and 20 characters")
	private String username;
	@NotBlank(message = "Password is required")
	@Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
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
