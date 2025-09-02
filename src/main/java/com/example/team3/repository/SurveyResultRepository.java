package com.example.team3.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.team3.domain.SurveyResult;
import com.example.team3.domain.User;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {

	   // 여기서 User 객체를 받아서 해당 유저의 설문 결과 리스트를 반환하는 메서드 선언
	
	// 기존 데이터 반환하는거
	List<SurveyResult> findAllByUsername (User user); 
	
	// 업데이트 용 메서드 추가
	Optional<SurveyResult> findByUsername(User user);
}
