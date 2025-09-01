package com.example.team3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team3.domain.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	Optional<User> findByUsername(String username);
	
	boolean existsByNickname(String nickname); //닉네임 중복체크 용도 UserService에서 사용
	
}