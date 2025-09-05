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
	 * 로그인 시도 시 프론트에서 받아온 이메일로 레코드 검색 후
	 * DB에 있으면 : 해당 레코드 반환
	 * DB에 없으면 : insertOauthUser 메서드를 실행하여 새롭게 DB에 저장 (회원가입 과정임)
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
		// 소셜 로그인은 비밀번호가 들어오지 않는데, User 엔티티는 password를 not null로 받고 있음
		// 오로지 DB에 넣기 위해 not null 조건을 충족하기 위한 목적으로 랜덤 비밀번호 생성
		String randomPassword = UUID.randomUUID().toString();
		
		user.setPassword(passwordEncoder.encode(randomPassword));
		user.setRole(RoleType.USER);
		user.setOauth(oAuthType);
		
		return userRepository.save(user);
	}
	
}

