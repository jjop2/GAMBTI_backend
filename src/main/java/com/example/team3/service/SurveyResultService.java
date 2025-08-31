package com.example.team3.service;

import javax.management.RuntimeErrorException;

import org.springframework.stereotype.Service;

import com.example.team3.domain.Genre;
import com.example.team3.domain.SurveyResult;
import com.example.team3.domain.User;
import com.example.team3.repository.GenreRepository;
import com.example.team3.repository.SurveyResultRepository;
import com.example.team3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SurveyResultService {

	public final SurveyResultRepository surveyResultRepository;
	
	public final GenreRepository genreRepository;
	public final UserRepository userRepository;
	
	public void saveSurveyResult (SurveyResult surveyResult, User user) { 
		
			// 변수 발생 차단
			if (surveyResult.getPreferGenre1() == null || surveyResult.getPreferGenre2() == null) {
				throw new RuntimeException("선호 장르를 입력해야 합니다.");
			}
		
		
		 	Genre g1 = genreRepository.findById(surveyResult.getPreferGenre1().getId())
		 				.orElseThrow( () -> new RuntimeException("첫번째 장르 없음"));
		    Genre g2 = genreRepository.findById(surveyResult.getPreferGenre2().getId())
		    			.orElseThrow(() -> new RuntimeException("두번째 장르 없음"));
		  

		    surveyResult.setPreferGenre1(g1);
		    surveyResult.setPreferGenre2(g2);
		    surveyResult.setUser(user);

		    surveyResultRepository.save(surveyResult);
	}
}

