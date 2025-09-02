package com.example.team3.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.team3.domain.Board;
import com.example.team3.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	@PostMapping("/board")
	public ResponseEntity<?> insertBoard(@RequestBody Board board){
		boardService.insertBoard(board);
		return new ResponseEntity<>("게시글 등록 완료", HttpStatus.OK);
	}
	
	@GetMapping("/board/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Integer id){
		Board board = boardService.getBoard(id);
		return new ResponseEntity<>(board, HttpStatus.OK);
		
	}
	
	// 보드 전체리스트 받기 안해놓음 (getBoardList)
	
}
