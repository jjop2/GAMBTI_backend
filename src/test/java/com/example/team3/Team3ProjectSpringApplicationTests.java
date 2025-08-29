package com.example.team3;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.team3.domain.User;
import com.example.team3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@SpringBootTest

class Team3ProjectSpringApplicationTests {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	@Test
	void contextLoads() {
		
		User user = userRepository.findById(1).get();
		System.out.println(passwordEncoder.matches("1234", user.getPassword()));
	}

}
