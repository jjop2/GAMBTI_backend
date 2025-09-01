package com.example.team3.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.team3.domain.Game;
import com.example.team3.domain.GameApiResponse;
import com.example.team3.domain.GameData;
import com.example.team3.domain.GameGenre;
import com.example.team3.domain.Genre;
import com.example.team3.domain.SteamGermany;
import com.example.team3.repository.GameGenreRepository;
import com.example.team3.repository.GameRepository;
import com.example.team3.repository.GenreRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class GameService {
    private final RestTemplate restTemplate;
    private final GameRepository gameRepository;
    private final GenreRepository genreRepository;
    private final GameGenreRepository gameGenreRepository;
    
    @Transactional
    public void saveGameFromSteamApi(int appid) {
    	 String apiUrl = UriComponentsBuilder.fromUriString("https://store.steampowered.com/api/appdetails")
                 .queryParam("appids", appid)
                 .queryParam("l", "korean") 
                 .toUriString();
        
        
            // RestTemplate으로 API 호출 및 JSON 파싱
        	GameApiResponse apiResponse = restTemplate.getForObject(apiUrl, GameApiResponse.class);
            
            if (apiResponse != null && apiResponse.getAppDetails() != null && apiResponse.getAppDetails().isSuccess()) {
                GameData gameData = apiResponse.getAppDetails().getData();
                Game game = new Game();
                String minRequirements = gameData.getPc_requirements().getMinimum();
                String cleanRequirements = minRequirements.replaceAll("<[^>]*>", "");
                
                // DTO 데이터를 JPA 엔티티로 매핑
                game.setSteam_appid(appid);
                game.setName(gameData.getName());
                game.setHeader_image(gameData.getHeader_image());
                game.setPc_requirements(cleanRequirements);
                game.setRelease_date(gameData.getRelease_date().getDate());
                
                // 나이 예외발생 처리
                String age = null;

                try {
                	age = gameData.getRatings().getSteamGermany().getRequired_age();
                } catch (Exception e) {
					age = "전체이용가";
				}
                
                if(age.equals("0"))
                	age="전체이용가";
                
                game.setRequired_age(age);
                game.setWebm(gameData.getMovies().get(0).getWebm().getMax());
                
                if (gameData.getScreenshots() != null && !gameData.getScreenshots().isEmpty()) {
                    game.setPath_thumbnail(gameData.getScreenshots().get(0).getPath_thumbnail());
                    game.setPath_full(gameData.getScreenshots().get(0).getPath_full());
                }
                
                // 나이 데이터 처리 및 DB에 저장
//                SteamGermany steamGermany = gameData.getRatings().getSteamGermany();
                
//                String requiredAge;
//                
//                if(steamGermany == null) {
//                	requiredAge = "전체이용가";
//                }else {
//                	String ageFromApi = steamGermany.getRequired_age();
//                	if( ageFromApi == null || ageFromApi.isEmpty()) {
//                		requiredAge = "전체이용가";
//                	}else {
//                		requiredAge = ageFromApi;
//                	}
//                }
//                game.setRequiredAge(requiredAge);

                
                
                // 장르 데이터 처리 및 DB에 저장
                if (gameData.getGenres() != null && !gameData.getGenres().isEmpty()) {
//                    String genreDescription = gameData.getGenres().get(0).getDescription();
//
//                    // 1. DB에서 장르를 찾습니다.
//                    Optional<Genre> existingGenre = genreRepository.findByDescription(genreDescription);
//                    
//                    
//                    // 2. 장르가 존재할 때만 Game 엔티티에 연결합니다.
//                    existingGenre.ifPresent(genre -> {
//                        game.setGenre(genre);
//                    });
                    
                	Game savedData = gameRepository.save(game);
                	
                    List<GameGenre> insertData = new ArrayList<>();
                    
                    for(Genre genre : gameData.getGenres()) {
                    	GameGenre gg = new GameGenre();
                    	if(genre.getDescription().equals("액션")) {
                        	genre.setId(1);
                        	gg.setGenre(genre);
                        	insertData.add(gg);
                        }else if(genre.getDescription().equals("시뮬레이션")) {
                        	genre.setId(2);
                        	gg.setGenre(genre);
                        	insertData.add(gg);
                        }else if(genre.getDescription().equals("레이싱")) {
                        	genre.setId(3);
                        	gg.setGenre(genre);
                        	insertData.add(gg);
                        }else if(genre.getDescription().equals("스포츠")){
                        	genre.setId(4);
                        	gg.setGenre(genre);
                        	insertData.add(gg);
                        }else if(genre.getDescription().equals("RPG")){
                        	genre.setId(5);
                        	gg.setGenre(genre);
                        	insertData.add(gg);
                        }
                		gg.setGame(savedData);
                    	
                		if(gg.getGenre() != null)
                			gameGenreRepository.save(gg);
                    }
//                    game.setGenre(insertData);
                }
                
//                Genre genre = new Genre();
//                
//                if(gameData.getGenres().get(0).getDescription().equals("Action")) {
//                	genre.setId(1);
//                	game.setGenre(genre);
//                }else if(gameData.getGenres().get(0).getDescription().equals("시뮬레이션")) {
//                	genre.setId(2);
//                	game.setGenre(genre);
//                }else if(gameData.getGenres().get(0).getDescription().equals("레이싱")) {
//                	genre.setId(3);
//                	game.setGenre(genre);
//                }else if(gameData.getGenres().get(0).getDescription().equals("스포츠")){
//                	genre.setId(4);
//                	game.setGenre(genre);
//                }else if(gameData.getGenres().get(0).getDescription().equals("RPG")){
//                	genre.setId(5);
//                	game.setGenre(genre);
//                }
                
                
                
//                System.out.println(game);
                // 게임 정보 최종 저장
//                gameRepository.save(game);
            
        } 
    }
    // GameRepository에서 만든 게임 찾아주는 메서드 호출해서 사용
    public List<Game> getRecommendGames(Integer userId){
    	return gameRepository.findSurveyResultGame(userId);
    }
}