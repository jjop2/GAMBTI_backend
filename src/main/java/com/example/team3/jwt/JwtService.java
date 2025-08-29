package com.example.team3.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JwtService {
	
	static final long EXPIRATIONTIME = 24 * 60 * 60 * 1000;
	
	static final String PREFIX = "Bearer ";
	
	static final SecretKey KEY = Jwts.SIG.HS256.key().build();
	
	static final String ROLES_CLAIM = "roles";
	
	/*
	 * 토큰 생성하는 메서드
	 */
	public String createToken(String username, Collection<? extends GrantedAuthority> authorities) {
		
		Date now = new Date();
		
		Date exp = new Date(now.getTime() + EXPIRATIONTIME);
		
		List<String> roles = (authorities == null)
				? List.of()
				: authorities.stream()
					.map(GrantedAuthority::getAuthority)
					.toList();
		
		return Jwts.builder()
				.subject(username)
				.issuedAt(now)
				.expiration(exp)
				.signWith(KEY)
				.claim(ROLES_CLAIM, roles)
				.compact();
		
	}
	
	/*
	 * 요청 받았을 때 토큰 꺼내오는 메서드
	 */
	public String resolveToken(HttpServletRequest request) {
		String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if(header != null && header.startsWith(PREFIX)) {
			return header.substring(PREFIX.length()).trim();
		}
		
		return null;
	}
	
	/*
	 * 토큰 유효성 검사하는 메서드
	 */
	public boolean validate(String token) {
		if(token == null || token.isBlank())
			return false;
		
		try {
			Jwts.parser()
				.verifyWith(KEY)
				.clockSkewSeconds(30)
				.build()
				.parseSignedClaims(token);
			
			return true;
		} catch (ExpiredJwtException e) {
			return false;
		} catch (JwtException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	/* 토큰에서 유저네임 꺼내오는 메서드 */
	public String getUsername(String token) {
		Claims claims = Jwts.parser()
							.verifyWith(KEY)
							.build()
							.parseSignedClaims(token)
							.getPayload();
		
		return claims.getSubject();
	}
	
	/* 토큰에서 권한 꺼내오는 메서드 */
	public List<? extends GrantedAuthority> getAuthorities(String token) {
		Claims claims = Jwts.parser()
							.verifyWith(KEY)
							.build()
							.parseSignedClaims(token)
							.getPayload();
		
		Object raw = claims.get(ROLES_CLAIM);
		
		if(raw instanceof List<?> list) {
			return list.stream()
					.filter(String.class::isInstance)
					.map(String.class::cast)
					.map(SimpleGrantedAuthority::new)
					.collect(Collectors.toList());
		}
		
		return List.of();
	}
	
}
