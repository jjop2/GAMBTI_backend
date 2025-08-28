package com.example.team3.jwt;

import java.io.IOException;
import java.util.List;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {
	
	private final JwtService jwtService;

	// 컨트롤러로 넘어가기 전, 요청 jwt 검사 후 유효한지에 따라 인증 정보 등록
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		// 토큰 추출
		String jwt = jwtService.resolveToken(request);
		// 토큰 유효성 검사
		boolean check = jwtService.validate(jwt);
		
		if(check) {
			String username = jwtService.getUsername(jwt);
			List<? extends GrantedAuthority> roles = jwtService.getAuthorities(jwt);
			
			Authentication auth = new UsernamePasswordAuthenticationToken(username, null, roles);
			
			SecurityContextHolder.getContext().setAuthentication(auth);
		}
		
		filterChain.doFilter(request, response);
	}

	
	
}
