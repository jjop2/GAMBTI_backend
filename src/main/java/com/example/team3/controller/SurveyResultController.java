package com.example.team3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.example.team3.config.AppConfig;
import com.example.team3.domain.SurveyResult;
import com.example.team3.service.SurveyResultService;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class SurveyResultController  {

	private final SurveyResultService surveyResultService;


	@PostMapping("/surveyresult")
	public ResponseEntity<?> saveUserSurveyResult (@RequestBody SurveyResult surveyResult, Authentication auth){
		
		
		System.out.println(surveyResult);
		System.out.println(auth.getName());
		// 요청 날린사람 username 
		
		surveyResultService.saveSurveyResult(surveyResult);
	
		// 게임 db에서 선호 장르 두가지 select해서 불러오는 작업 
		
		
		
		return new ResponseEntity<>("저장 완료", HttpStatus.OK);
	// 게임에 대한 리트나 목록 생기면 수정 예정 (응답할 때 게임 list 함께 보내기 
	} 

}
