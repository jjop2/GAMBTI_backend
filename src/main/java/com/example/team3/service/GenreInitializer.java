package com.example.team3.service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.example.team3.domain.Genre;



import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor

// 기능

//CommandLineRunner는 **인터페이스(interface)**야.

//이걸 implements 하면 스프링이:

//"오, 너는 서버 켜질 때 실행돼야 하는 클래스구나!"

public class GenreInitializer implements CommandLineRunner {

	public final GenreService genreService;
	

	@Transactional
	@Override
	public void run(String... args) {

	

		if (genreService.getAllGenre().isEmpty()) {

			genreService.saveGenre(new Genre(null, "🔫\"속도와 기술로 한계를 넘는 질주의 세계.", "Action"));
			genreService.saveGenre(new Genre(null, "🎮\"현실을 조작하며 세상을 직접 설계하는 장르.", "Simulation" ));
			genreService.saveGenre(new Genre(null, "🏎️\"속도와 기술로 한계를 넘는 질주의 세계.", "Racing"));
			genreService.saveGenre(new Genre(null, "🏅\"전략과 컨트롤로 승부를 겨루는 e-스포츠의 정수.", "Sports"));
			genreService.saveGenre(new Genre(null, "🐉\"캐릭터와 함께 성장하며 서사를 이끄는 모험 장르.", "RPG"));
//	

		}
	}

}
