package com.example.team3.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.team3.domain.Board;
import com.example.team3.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	
	// 단일 게시글 조회
	@GetMapping("/upload/{id}")
	public ResponseEntity<?> getBoard(@PathVariable Integer id){
		Board board = boardService.getBoard(id);
		return new ResponseEntity<>(board, HttpStatus.OK);
	}
	
	// 전체 게시글 조회
	@GetMapping("/upload")
	public ResponseEntity<List<Board>> getBoardList() {
		List<Board> boardList = boardService.getBoardList();
		return new ResponseEntity<>(boardList, HttpStatus.OK);
	}
	
	// 게시글 삭제 기능
	@DeleteMapping("/upload/{id}")
	public  ResponseEntity<?> deleteBoard(@PathVariable Integer id) {
        boardService.deleteBoard(id);
        return new ResponseEntity<>("게시글이 삭제되었습니다.", HttpStatus.OK);
	}
	
	//게시글 수정 기능
	@PutMapping("/upload/{id}")
	@ResponseBody
	public ResponseEntity<?> updateBoard(@RequestBody Board board) {
		boardService.updateBoard(board);
		
		return new ResponseEntity<>("게시글이 수정되었습니다.", HttpStatus.OK);
	}
	
	// 게시판 이미지 출력 기능
	@GetMapping("/upload/file/{fileName}")
	public ResponseEntity<Resource> getImage(@PathVariable String fileName) throws IOException {
	    File file = new File("upload/" + fileName); // 실제 서버 저장 경로
	    if(!file.exists()) {
	        return ResponseEntity.notFound().build();
	    }
	    Resource resource = new FileSystemResource(file);

	    // 파일 확장자에 따라 MediaType 변경 가능
	    return ResponseEntity.ok()
	            .contentType(MediaType.IMAGE_JPEG) // jpg 기준, png면 MediaType.IMAGE_PNG
	            .body(resource);
	}
	
	
	

}
