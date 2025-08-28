package com.example.team3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.team3.domain.User;
import com.example.team3.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class Usercontroller {
	
	private final UserService userService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user){
		System.out.println(user);
		userService.insert(user);
		
		return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
	}
}
