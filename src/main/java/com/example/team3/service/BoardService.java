package com.example.team3.service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.team3.DTO.BoardRequestDTO;
import com.example.team3.domain.Board;
import com.example.team3.domain.User;
import com.example.team3.repository.BoardRepository;
import com.example.team3.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	
	
	// 게시글 등록 서비스
	public void insertBoard(BoardRequestDTO boardRequestDTO, MultipartFile file) {
		
		
		Board board = new Board();
		board.setTitle(boardRequestDTO.getTitle());
		board.setContent(boardRequestDTO.getContent());
		board.setImg(file.getOriginalFilename());
		
		// 작성자 user 레코드 찾기
		User user = userRepository.findById(boardRequestDTO.getWriterId())
				.orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다"));
		board.setUser(user);
		
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
	
	// 특정 사용자 게시글 조회 서비스
	public List<Board> getMyBoards(Integer id) {
		
		return boardRepository.findByUserIdOrderByIdDesc(id);
	}
	
}
