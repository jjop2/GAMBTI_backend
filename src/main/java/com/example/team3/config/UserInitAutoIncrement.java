package com.example.team3.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

/*
 * User 엔티티의 기본키 id의 기본값을 100000으로 설정하기 위해 필요한 컴포넌트
 * mysql에서 직접 쿼리문 작성하는 건 번거로우므로 아래 코드를 통해
 * 스프링부트 실행 시 아래의 쿼리문을 실행하게 함
 * 자세한 건 '노션 회의 페이지 - 프로젝트 관련 안내 - 스프링부트(JPA)-mysql에서 id(pk)의 기본값 설정하기' 참고
 */

@Component
@RequiredArgsConstructor
public class UserInitAutoIncrement implements CommandLineRunner {

	private final JdbcTemplate jdbcTemplate;
	
	@Override
	public void run(String... args) throws Exception {
		try {
			jdbcTemplate.execute("ALTER TABLE user AUTO_INCREMENT = 100000");
		} catch (Exception e) {
			System.out.println("AUTO_INCREMENT 설정 스킵됨: " + e.getMessage());
		}
		
	}

	
}
