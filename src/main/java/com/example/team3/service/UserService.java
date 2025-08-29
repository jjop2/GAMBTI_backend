package com.example.team3.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.team3.domain.User;
import com.example.team3.domain.OAuthType;
import com.example.team3.domain.RoleType;

import com.example.team3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public User getUser(String username) {
		User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username + " 사용자를 찾을 수 없습니다."));
		
		 return user;
	}
	
	
	public void insert(User user) {
		
		
		user.setPassword(passwordEncoder.encode(user.getPassword())); 
		user.setRole(RoleType.USER);
		user.setOauth(OAuthType.BOARD);
		
		userRepository.save(user);
	}

}
