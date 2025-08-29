package com.example.team3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.team3.domain.Genre;
import com.example.team3.repository.GenreRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GenreService {

	
	private final GenreRepository genreRepository;

	
	public List<Genre> getAllGenre () {
		
		return genreRepository.findAll();
		
	}
	
	public Genre saveGenre (Genre genre) {
		
		return genreRepository.save(genre);
	}
}
	
	
	
	
	
	
	
	

