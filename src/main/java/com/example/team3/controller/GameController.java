package com.example.team3.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.team3.domain.Game;
import com.example.team3.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping
@RequiredArgsConstructor
public class GameController {
	
    private final GameService gameService;

    @PostMapping("/fetch/{appid}")
    public String fetchAndSaveGame(@PathVariable int appid) {
        gameService.saveGameFromSteamApi(appid);
        return "게임 정보를 성공적으로 저장했습니다!";
    }
    
    @GetMapping("/recommendgame/{userId}")
    public List<Game> getRecommendGames(@PathVariable Integer userId){
    	return gameService.getRecommendGames(userId);
    }
    
    @GetMapping("/recommendgame/genre/{genreId}")
    public List<Game> getFirstGenreGameList(@PathVariable Integer genreId){
    	return gameService.getFirstGenreGameList(genreId);
    }
}
