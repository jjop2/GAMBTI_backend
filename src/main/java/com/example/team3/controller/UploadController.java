package com.example.team3.controller;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.team3.domain.Board;
import com.example.team3.repository.BoardRepository;

@RestController
public class UploadController {
	
	@Autowired
	private BoardRepository boardRepository;
	
	@PostMapping("/upload")
	public ResponseEntity<?> upload(Board board, MultipartFile file){
		System.out.println(board);
		System.out.println(file);
		
		try {
			Path path = Paths.get("upload/" + file.getOriginalFilename());
			Files.createDirectories(path.getParent());
			Files.write(path, file.getBytes());
			board.setImg(file.getOriginalFilename());
			boardRepository.save(board);
			return new ResponseEntity<>("업로드 성공", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("업로드 실패",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
}
