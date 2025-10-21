package com.jonathan.todo.service;

import java.util.Optional;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.jonathan.todo.dto.request.LoginRequest;
import com.jonathan.todo.dto.request.RegisterRequest;
import com.jonathan.todo.dto.response.LoginResponse;
import com.jonathan.todo.dto.response.RegisterResponse;
import com.jonathan.todo.exception.InvalidCredentialsException;
import com.jonathan.todo.exception.UserAlreadyExistsException;
import com.jonathan.todo.model.User;

import jakarta.transaction.Transactional;

@Service
public class AuthService {
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;
	public final MessageSource messageSource;
	
	public AuthService(UserService userService, PasswordEncoder passwordEncoder, MessageSource messageSource) {
	    this.userService = userService;
	    this.passwordEncoder = passwordEncoder;
	    this.messageSource = messageSource;
	}
	
	@Transactional
	public RegisterResponse registerUser(RegisterRequest request) {
		try {
			User user = new User();
			user.setUsername(request.getUsername());
			String hashedPassword = passwordEncoder.encode(request.getPassword());
			user.setPassword(hashedPassword);
			User savedUser = userService.save(user);
			return new RegisterResponse(savedUser.getUserId(), savedUser.getUsername(), savedUser.getCreatedAt());
		}
		catch(DataIntegrityViolationException ex) {
			throw new UserAlreadyExistsException(messageSource.getMessage("user.exists", null, LocaleContextHolder.getLocale()));
		}
	}
	
	public LoginResponse loginUser(LoginRequest request) {
		Optional<User> optUser = userService.getUserByName(request.getUsername());
		if(optUser.isEmpty() || !passwordEncoder.matches(request.getPassword(), optUser.get().getPassword())) {
	            throw new InvalidCredentialsException(messageSource.getMessage("user.invalid.credentials", null, LocaleContextHolder.getLocale()));

		}
		return new LoginResponse(optUser.get().getUserId(), optUser.get().getUsername());
		
	}
}
