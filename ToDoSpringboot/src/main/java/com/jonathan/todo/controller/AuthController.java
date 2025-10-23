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
import com.jonathan.todo.dto.response.ApiResponse;
import com.jonathan.todo.dto.response.LoginResponse;
import com.jonathan.todo.dto.response.RegisterResponse;
import com.jonathan.todo.service.AuthService;
import com.jonathan.todo.util.MessageUtil;
import com.jonathan.todo.validation.OnLogin;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
    private final AuthService authService;
    private final MessageUtil messageUtil;

    public AuthController(AuthService authService, MessageUtil messageUtil) {
        this.authService = authService;
        this.messageUtil = messageUtil;
    }
	
	@PostMapping("/register")
	public ResponseEntity<ApiResponse<RegisterResponse>> register(@Valid @RequestBody RegisterRequest request) {
		RegisterResponse registerResponse = authService.registerUser(request);
		String successMessage =  messageUtil.getMessage("user.register.success");
		ApiResponse<RegisterResponse> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), successMessage, registerResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<ApiResponse<LoginResponse>> login(@Validated(OnLogin.class) @RequestBody LoginRequest request){
		LoginResponse loginResponse = authService.loginUser(request);
		String successMessage =  messageUtil.getMessage("user.login.success");
		ApiResponse<LoginResponse> apiResponse = new ApiResponse<>(HttpStatus.OK.value(), successMessage, loginResponse);
		return ResponseEntity.ok(apiResponse); 
	}
}
