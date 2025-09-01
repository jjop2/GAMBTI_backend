package com.example.team3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.team3.DTO.SurveyResultRequestDTO;
import com.example.team3.domain.Genre;
import com.example.team3.domain.SurveyResult;
import com.example.team3.domain.User;
import com.example.team3.repository.GenreRepository;
import com.example.team3.repository.SurveyResultRepository;
import com.example.team3.repository.UserRepository;
import com.example.team3.service.SurveyResultService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class SurveyResultController  {

    private final SurveyResultRepository surveyResultRepository;
	private final UserRepository userRepository;
	private final GenreRepository genreRepository;

	@PostMapping("/surveyresult")
	public ResponseEntity<?> saveUserSurveyResult (@RequestBody SurveyResultRequestDTO request, Authentication auth){
		
		if(auth == null || !auth.isAuthenticated()) {
			return new ResponseEntity<>("로그인 필요", HttpStatus.UNAUTHORIZED);
		} 
		
		System.out.println("받은 요청 DTO :" + request);
		
		String username = auth.getName();
		User user = userRepository.findByUsername(username).orElseThrow();
		
		   Genre g1 = genreRepository.findById(request.getPreferGenre1Id()).orElseThrow();
		   Genre g2 = genreRepository.findById(request.getPreferGenre2Id()).orElseThrow();

		   SurveyResult result = new SurveyResult();
		
		   result.setAge(request.getAge());
		   result.setGender(request.getGender());
		   result.setCombinationGenre(request.getCombinationGenre());
		   result.setPreferGenre1(g1);
		   result.setPreferGenre2(g2);
		   result.setUsername(user);
		   
		   surveyResultRepository.save(result);
		   
		
		return new ResponseEntity<>("저장 완료", HttpStatus.OK);
	// 게임에 대한 리트나 목록 생기면 수정 예정 (응답할 때 게임 list 함께 보내기 
	} 

}


//System.out.println(surveyResult);
//System.out.println(auth.getName());
//
//// 요청 날린사람 username 
//
//surveyResultService.saveSurveyResult(surveyResult);
//
//// 게임 db에서 선호 장르 두가지 select해서 불러오는 작업 	
