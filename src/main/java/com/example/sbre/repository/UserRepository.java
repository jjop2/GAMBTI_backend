package com.example.sbre.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.sbre.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
}
