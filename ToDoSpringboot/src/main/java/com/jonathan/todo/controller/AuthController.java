package com.jonathan.todo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jonathan.todo.dto.request.LoginRequest;
import com.jonathan.todo.dto.request.RegisterRequest;
import com.jonathan.todo.dto.response.LoginResponse;
import com.jonathan.todo.dto.response.RegisterResponse;
import com.jonathan.todo.service.AuthService;
import com.jonathan.todo.validation.OnLogin;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }
	
	@PostMapping("/register")
	public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) {
		RegisterResponse response = authService.registerUser(request);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> login(@Validated(OnLogin.class) @RequestBody LoginRequest request){
		LoginResponse response = authService.loginUser(request);
		return ResponseEntity.ok(response); 
	}
}
