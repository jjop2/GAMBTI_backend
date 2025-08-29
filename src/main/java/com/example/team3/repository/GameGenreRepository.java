package com.example.team3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.team3.domain.GameGenre;

@Repository
public interface GameGenreRepository extends JpaRepository<GameGenre, Integer> {

}
