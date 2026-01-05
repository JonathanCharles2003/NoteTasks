package com.jonathan.todo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jonathan.todo.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username); // check if this and below are mandatory
	
	boolean existsByUsername(String username);
}
