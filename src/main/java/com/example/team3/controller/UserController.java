package com.example.team3.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.team3.domain.User;
import com.example.team3.domain.UserDTO;
import com.example.team3.jwt.JwtService;
import com.example.team3.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class UserController {
	
	private final UserService userService;
	private final AuthenticationManager authenticationManager;
	private final JwtService jwtService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody User user){
		userService.insert(user);
		
		return new ResponseEntity<>("회원가입 완료", HttpStatus.OK);
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody User user) {
		UsernamePasswordAuthenticationToken cred = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
		
		Authentication auth = authenticationManager.authenticate(cred);
		
		String jwt = jwtService.createToken(auth.getName(), auth.getAuthorities());
		
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
	}
	
	@GetMapping("/userinfo")
	public ResponseEntity<?> userInfo(Authentication auth) {
		User user = userService.getUser(auth.getName());
		UserDTO dto = new UserDTO(user);
		
		return new ResponseEntity<>(dto, HttpStatus.OK);
	}
	
	@PutMapping("/usermodify")
	public ResponseEntity<?> updateUserinfo(Authentication auth, @RequestBody User user){
		try {
			userService.updateUser(auth.getName(),user);
			return new ResponseEntity<>("수정 완료되었습니다",HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("수정 실패"+e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteUser(Authentication auth){
		try {
			User user = userService.getUser(auth.getName());
			userService.deleteUser(user.getId());
			return new ResponseEntity<>("계정 탈퇴 완료",HttpStatus.OK);
			
		} catch(Exception e) {
			return new ResponseEntity<>("계정 탈퇴 실패:"+ e.getMessage(),HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
