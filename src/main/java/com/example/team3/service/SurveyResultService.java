package com.example.team3.service;

import org.springframework.stereotype.Service;

import com.example.team3.domain.SurveyResult;
import com.example.team3.repository.SurveyResultRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyResultService {

	public final SurveyResultRepository surveyResultRepository;
	
	
	public void saveSurveyResult (SurveyResult surveyResult) { 
		
		surveyResultRepository.save(surveyResult);
	
	}
}
