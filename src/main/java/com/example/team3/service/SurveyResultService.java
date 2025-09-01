package com.example.team3.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.team3.DTO.SurveyResultRequestDTO;
import com.example.team3.DTO.SurveyResultResponseDTO;
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


	// 유저가 새롭게 테스트할 경우 기존 데이터 지우고 업데이트로 변경 
	// 지우고 업데이트 메서드 명 수정했습니다! 
	public void saveOrUpdateSurveyResult(SurveyResultRequestDTO request, User user) {

		Genre g1 = genreRepository.findById(request.getPreferGenre1Id()).orElseThrow();
		Genre g2 = genreRepository.findById(request.getPreferGenre2Id()).orElseThrow();
	
												
		SurveyResult existingResult = surveyResultRepository.findByUsername(user).orElse(null);
		SurveyResult result;

		if (existingResult != null)	{
			result = existingResult;
		} else { 
			result = new SurveyResult();
		}
		   result.setAge(request.getAge());
		   result.setGender(request.getGender());
		   result.setCombinationGenre(request.getCombinationGenre());
		   result.setPreferGenre1(g1);
		   result.setPreferGenre2(g2);
		   result.setUsername(user);
		   
		   System.out.println("세이브하기 전 값 셋팅"+ result);
		   surveyResultRepository.save(result);

	}

	public List<SurveyResultResponseDTO> getSurveyResultByUser(User user) {
		List<SurveyResult> results = surveyResultRepository.findAllByUsername(user); 

		// surveyResult List로 만들어서 // user 객체 받아서 username으로 모든 데이터 조회 
		
		
		// DTO 형식에 맞게 다시 한번 => 패키징하고 => 리스트로 만들어서 리턴! 
		return results.stream()
					  .map(r -> new SurveyResultResponseDTO(
					  r.getAge(),
					  r.getGender(),
					  r.getPreferGenre1().getId(),
					  r.getPreferGenre2().getId(),
					  r.getCombinationGenre(),
					  r.getUsername().getId()
					  ))
					  .collect(Collectors.toList());
							  
				
	}
}
