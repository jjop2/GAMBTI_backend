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
	
	
	// 게시글 등록 서비스
	public void insertBoard(Board board) {
		boardRepository.save(board);
	}
	
	// 게시글 전체조회 서비스
	public List<Board> getBoardList(){
		return boardRepository.findAllByOrderByIdDesc();
	}
	
	// 게시글 단일 조회 서비스
	public Board getBoard(Integer id) {
		
		return boardRepository.findById(id).get();
	}
	
	// 게시글 삭제 서비스
	public void deleteBoard(Integer id) {
		if(!boardRepository.existsById(id)) {
			throw new IllegalArgumentException("삭제할 게시글이 존재하지 않습니다.");
		}
		boardRepository.deleteById(id);
	}
	
	// 게시글 수정 서비스
	public void updateBoard(Board board) {
		Board originalBoard = getBoard(board.getId());
		
		originalBoard.setTitle(board.getTitle());
		originalBoard.setContent(board.getContent());
		originalBoard.setImg(board.getImg());
		
		boardRepository.save(originalBoard);
	}
	
}
