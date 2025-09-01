package com.example.team3.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.team3.domain.Genre;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{
	Optional<Genre> findByDescription(String description);
}

