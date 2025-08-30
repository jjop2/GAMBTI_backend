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
import com.example.team3.domain.RoleType;
import com.example.team3.domain.User;
import com.example.team3.jwt.JwtService;
import com.example.team3.security.UserDetailsImpl;
import com.example.team3.service.OauthLoginService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class OauthLoginController {
	
	private final OauthLoginService oauthLoginService;
	private final JwtService jwtService;
	
	/*
	 * 프론트에서 /login/kakao 또는 /login/google로 요청 받음
	 * 해당 OAuthType에 따라 로그인 혹은 회원가입 
	 */
	@PostMapping("/login/{provider}")
	public ResponseEntity<?> oauthLogin(@PathVariable("provider") String provider, @RequestBody User user) {
		
		// provider에 kakao냐 google이냐에 따라 OAuthType을 정하기
		OAuthType oAuthType;
		switch(provider.toLowerCase()) {
		case "kakao": oAuthType = OAuthType.KAKAO; break;
		case "google": oAuthType = OAuthType.GOOGLE; break;
		// 만약 kakao나 google이 아닌 경우 예외 처리
		default: throw new IllegalArgumentException("지원하지 않는 로그인 제공자입니다.");
		}
		
		// 소셜 로그인 사용자의 정보를 DB에서 조회 (또는 신규 생성)
		User oauthUser = oauthLoginService.getOauthUser(user, oAuthType);
		// 토큰에 담을 id 뽑아내는 과정은 일반 로그인과 유사함
		// UserController - login 메서드 참고
		UserDetailsImpl userDetailsImpl = new UserDetailsImpl(oauthUser);
		Integer id = userDetailsImpl.getUser().getId();
		
		// 일반 로그인과 달리 소셜 로그인은 비밀번호 검증 과정이 없음
		// 직접 UsernamePasswordAuthenticationToken을 만들어 인증 객체 구성
		Authentication auth = new UsernamePasswordAuthenticationToken(
				userDetailsImpl,
				null,	// 패스워드 x
				List.of(new SimpleGrantedAuthority("ROLE_USER"))
		);
	
		
		// JWT 생성 (username, 권한, id 포함)
		String jwt = jwtService.createToken(auth.getName(), auth.getAuthorities(), id);
		
		return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
				.header(HttpHeaders.ACCESS_CONTROL_EXPOSE_HEADERS, "Authorization")
				.build();
		
	}
	
}
