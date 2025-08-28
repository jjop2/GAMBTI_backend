package com.example.team3.jwt;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;

@Service
public class JwtService {
	
	static final long EXPIRATIONTIME = 24 * 60 * 60 * 1000;
	
	static final String PREFIX = "Bearer ";
	
	static final SecretKey KEY = Jwts.SIG.HS256.key().build();
	
	static final String ROLES_CLAIM = "roles";
	
	
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
	
}
