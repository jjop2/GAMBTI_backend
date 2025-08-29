package com.example.team3.controller;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.team3.domain.OAuthType;
import com.example.team3.domain.User;
import com.example.team3.jwt.JwtService;
import com.example.team3.service.OauthLoginService;
import com.example.team3.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OauthLoginController {
	
	private final OauthLoginService oauthLoginService;
	private final JwtService jwtService;
	
//	@PostMapping("/login/{provider}")
//	public ResponseEntity<?> oauthLogin(@PathVariable String provider @RequestBody User user) {
//		User kakaoUser = oauthLoginService.getOauthUser(user, OAuthType.KAKAO);
//		
//		// 인증 객체
//		Authentication auth = new UsernamePasswordAuthenticationToken(
//				kakaoUser.getUsername(),
//				null,
//				List.of(new SimpleGrantedAuthority("ROLE_USER"))
//		);
//		
//		// 토큰 발급
//		String jwt = jwtService.createToken(auth.getName(), auth.getAuthorities());
//		
//		return ResponseEntity.ok()
//				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
//				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
//				.build();
//		
//	}
//	
//	

	@PostMapping("/login/kakao")
	public ResponseEntity<?> kakaoLogin(@RequestBody User user) {
		User kakaoUser = oauthLoginService.getOauthUser(user, OAuthType.KAKAO);
		
		// 인증 객체
		Authentication auth = new UsernamePasswordAuthenticationToken(
				kakaoUser.getUsername(),
				null,
				List.of(new SimpleGrantedAuthority("ROLE_USER"))
		);
		
		// 토큰 발급
		String jwt = jwtService.createToken(auth.getName(), auth.getAuthorities());
		
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		
	}
	
	@PostMapping("/login/google")
	public ResponseEntity<?> googleLogin(@RequestBody User user) {
		User googleUser = oauthLoginService.getOauthUser(user, OAuthType.GOOGLE);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(
				googleUser.getUsername(),
				null,
				List.of(new SimpleGrantedAuthority("ROLE_USER"))
		);
		
		String jwt = jwtService.createToken(auth.getName(), auth.getAuthorities());
		
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		
	}
	
}
