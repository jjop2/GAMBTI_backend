package com.example.team3.security;

import java.io.IOException;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		
		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
		response.setContentType("application/json; charset=utf-8");
		
		// 인증 실패 시 보낼 값
		String json = "{"
                + "\"status\": 401,"
                + "\"error\": \"Unauthorized\","
                + "\"message\": \"인증이 필요합니다\""
                + "}";
		
		response.getWriter().write(json);
		
	}
	
}
