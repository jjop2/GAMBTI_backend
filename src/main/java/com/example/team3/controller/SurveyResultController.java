package com.example.team3.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.team3.DTO.SurveyResultRequestDTO;
import com.example.team3.DTO.SurveyResultResponseDTO;
import com.example.team3.domain.User;
import com.example.team3.repository.UserRepository;
import com.example.team3.service.SurveyResultService;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class SurveyResultController  {

  
	private final UserRepository userRepository;

	private final SurveyResultService surveyResultService;

	@PostMapping("/surveyresult")
	public ResponseEntity<?> saveUserSurveyResult (@RequestBody SurveyResultRequestDTO request, Authentication auth){
		
		if(auth == null || !auth.isAuthenticated()) {
			return new ResponseEntity<>("로그인 필요", HttpStatus.UNAUTHORIZED);
		} 
		
		System.out.println("받은 요청 DTO :" + request);
		
		String username = auth.getName();
		User user = userRepository.findByUsername(username).orElseThrow();
		
		surveyResultService.saveOrUpdateSurveyResult(request, user);
		   
		return new ResponseEntity<>("저장 완료", HttpStatus.OK);
	// 게임에 대한 리트나 목록 생기면 수정 예정 (응답할 때 게임 list 함께 보내기 
	} 
	
	// 승오님 백처리로 프론트에서 게임 정보 확인 가능 
	// 프론트에서 요청오면 userId로 조회한 메서드 호출해서 => db 꺼내서 프론트에 보내줄거임

	@GetMapping("/surveyresult")
	public ResponseEntity<?> getUserSurveyResult(Authentication auth) {

		if (auth == null || !auth.isAuthenticated()) 
			return new ResponseEntity<>("로그인이 필요합니다", HttpStatus.UNAUTHORIZED);
		
		String username = auth.getName();
		User user = userRepository.findByUsername(username)
				.orElseThrow( () -> new RuntimeException("해당 유저를 찾을 수 없습니다."));
		
		   List<SurveyResultResponseDTO> results = surveyResultService.getSurveyResultByUser(user);
		
		   System.out.println(results);
		return ResponseEntity.ok(results);
	}

}


