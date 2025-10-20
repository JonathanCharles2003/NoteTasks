package com.jonathan.todo.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.jonathan.todo.model.User;
import com.jonathan.todo.repository.UserRepository;

@Service
public class UserService {
	private final UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		this.userRepo = userRepo;
	}
	
	public User save(User user) {
		 return userRepo.save(user);
	}
	
	public Optional<User> getUserByName(String username) {
		return userRepo.findByUsername(username);	
	}
	
	public boolean checkUserExists(String username) {
		return userRepo.existsByUsername(username);
	}
	
}
