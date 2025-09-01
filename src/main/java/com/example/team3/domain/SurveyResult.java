package com.example.team3.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class SurveyResult {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String age;
	
	private String gender;
	
	
	@ManyToOne
	@JoinColumn(name = "preferGenre_1")
	private Genre preferGenre1;

	@ManyToOne
	@JoinColumn(name = "preferGenre_2")
	private Genre preferGenre2;

	private String combinationGenre;
	
	@OneToOne(fetch = FetchType.EAGER)  // 유저 정보 바로 바로 불러올 수 있도록
	@JoinColumn(name = "user_id", unique = true)  // DB에 user_Id 중복으로 들어가지 않게 unique 설정 
	private User username;
	
}

//@Enumerated(EnumType.STRING)
//private GenreType type;


