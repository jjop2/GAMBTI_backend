package com.example.team3.controller;

import org.springframework.web.bind.annotation.*;

import com.example.team3.service.GameService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/games")
@RequiredArgsConstructor
public class GameController {
    private final GameService gameService;

    @PostMapping("/fetch/{appid}")
    public String fetchAndSaveGame(@PathVariable int appid) {
        gameService.saveGameFromSteamApi(appid);
        return "게임 정보를 성공적으로 저장했습니다!";
    }
}
