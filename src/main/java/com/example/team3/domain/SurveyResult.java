package com.example.team3.domain;

import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User username;
	
}

//@Enumerated(EnumType.STRING)
//private GenreType type;


