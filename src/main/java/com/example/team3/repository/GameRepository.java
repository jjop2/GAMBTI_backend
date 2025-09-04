package com.example.team3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.team3.domain.Game;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
	
//	@Query(value = "SELECT GM.* FROM survey_result AS SR JOIN game_genre AS GG ON GG.genre_id IN (SR.prefer_genre_1,SR.prefer_genre_2) JOIN game AS GM ON GG.game_id = GM.id WHERE SR.user_id = :userId ",nativeQuery = true)
	@Query(value = "SELECT GM.* FROM survey_result AS SR JOIN game_genre AS GG ON GG.genre_id IN (SR.prefer_genre_1,SR.prefer_genre_2) JOIN game AS GM ON GG.game_id = GM.id WHERE SR.user_id = :userId GROUP BY GM.id HAVING COUNT(GG.genre_id) = 2", nativeQuery = true)
	List<Game>findSurveyResultGame(@Param("userId") Integer userId);
	
	@Query(value = "SELECT * FROM game AS g WHERE g.id IN (SELECT game_id FROM game_genre WHERE genre_id = :genreId)", nativeQuery = true)
	List<Game>findFirstGenreGameList(@Param("genreId") Integer genreId);
}
