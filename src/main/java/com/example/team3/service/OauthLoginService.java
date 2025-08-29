package com.example.team3.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.team3.domain.OAuthType;
import com.example.team3.domain.RoleType;
import com.example.team3.domain.User;
import com.example.team3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OauthLoginService {
	
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	/* 
	 * 소셜 로그인 메서드
	 */
	public User getOauthUser(User user, OAuthType oAuthType) {
		User oauthUser = userRepository.findByUsername(user.getUsername()).orElse(null);
		
		if(oauthUser == null) {
			oauthUser = insertOauthUser(user, oAuthType);
		}
				
		return oauthUser;
		
	}
	
	// 소셜 로그인 시도 시 새로운 사용자면 DB 저장 (회원가입)
	public User insertOauthUser(User user, OAuthType oAuthType) {
		// 소셜 로그인용 랜덤 비밀번호
		String randomPassword = UUID.randomUUID().toString();
		
		user.setPassword(passwordEncoder.encode(randomPassword));
		user.setRole(RoleType.USER);
		user.setOauth(oAuthType);
		
		return userRepository.save(user);
	}
	
}

