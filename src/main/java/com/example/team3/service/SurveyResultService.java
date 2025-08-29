package com.example.team3.service;

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
	
	public void saveSurveyResult (SurveyResult surveyResult) { 
		
		 	Genre g1 = genreRepository.findById(surveyResult.getPreferGenre1().getId()).orElseThrow();
		    Genre g2 = genreRepository.findById(surveyResult.getPreferGenre2().getId()).orElseThrow();
		    User user = userRepository.findById(surveyResult.getUsername().getId()).orElseThrow();

		    surveyResult.setPreferGenre1(g1);
		    surveyResult.setPreferGenre2(g2);
		    surveyResult.setUsername(user);

		    surveyResultRepository.save(surveyResult);
	}
}

