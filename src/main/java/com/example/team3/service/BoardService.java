package com.example.team3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.team3.domain.Board;
import com.example.team3.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public void insertBoard(Board board) {
		boardRepository.save(board);
	}
	
	public List<Board> getBoardList(){
		return boardRepository.findAllByOrderByIdDesc();
	}
	
	public Board getBoard(Integer id) {
		
		return boardRepository.findById(id).get();
	}
	
}
