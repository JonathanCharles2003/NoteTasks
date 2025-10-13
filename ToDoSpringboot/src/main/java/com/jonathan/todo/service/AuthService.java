package com.jonathan.todo.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jonathan.todo.dto.request.LoginRequest;
import com.jonathan.todo.dto.request.RegisterRequest;
import com.jonathan.todo.dto.response.LoginResponse;
import com.jonathan.todo.dto.response.RegisterResponse;
import com.jonathan.todo.exception.InvalidCredentialsException;
import com.jonathan.todo.model.User;

@Service
public class AuthService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	
	public AuthService(UserService userService, PasswordEncoder passwordEncoder) {
	    this.userService = userService;
	    this.passwordEncoder = passwordEncoder;
	}
	
	public RegisterResponse registerUser(RegisterRequest request) {

		User user = new User();
		
		user.setUsername(request.getUsername());
		String hashedPassword = passwordEncoder.encode(request.getPassword());
		user.setPassword(hashedPassword);
		User savedUser = userService.save(user);
		return new RegisterResponse(savedUser.getUserId(), savedUser.getUsername(), savedUser.getCreatedAt(),"User registered Successfully");
	}
	
	public LoginResponse loginUser(LoginRequest request) {
		Optional<User> optUser = userService.getUserByName(request.getUsername());
		if(optUser.isEmpty() || !passwordEncoder.matches(request.getPassword(), optUser.get().getPassword())) {
	            throw new InvalidCredentialsException("Invalid username or password");

		}
		return new LoginResponse(optUser.get().getUserId(), optUser.get().getUsername());
		
	}
}
