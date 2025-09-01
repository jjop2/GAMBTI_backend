package com.example.team3.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyResultResponseDTO {

	
	private String age;
    private String gender;
    private Integer preferGenre1Id;
    private Integer preferGenre2Id;
    private String combinationGenre;
    private Integer userId;
    
}
