package com.example.team3.service;

import org.springframework.stereotype.Service;


import com.example.team3.domain.RoleType;
import com.example.team3.domain.User;

import com.example.team3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	
	// 엔코더 작성
	
	public void insert(User user) {
		
		
		user.setPassword("test123"); //시큐리티 encoder세팅되면 작성
		user.setRole(RoleType.USER);
		
		userRepository.save(user);
	}
	public User getUser(String username) {
		return userRepository.findByUsername(username).get();
	}
	
}
