package com.example.team3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team3.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
	
}