package com.example.team3.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.team3.domain.Board;
import com.example.team3.domain.User;
import com.example.team3.repository.BoardRepository;
import com.example.team3.repository.UserRepository;
import com.example.team3.service.BoardService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService boardService;
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	
	// 게시글 등록
	@PostMapping("/upload")
	public ResponseEntity<?> upload(Board board, MultipartFile file){
		try {
//			User user = (User) userRepository.findById(board.getUser().getId());
			
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
	public ResponseEntity<?> updateBoard(Board board, MultipartFile file, @RequestParam(required = false) String existingImg) {
		try {
			// 파일이 새로 업로드된 경우만 처리
	        if (file != null && !file.isEmpty()) {
	            Path path = Paths.get("upload/" + file.getOriginalFilename());
	            Files.createDirectories(path.getParent());
	            Files.write(path, file.getBytes());
	            board.setImg(file.getOriginalFilename());
	        } else if (existingImg != null) {
	            // 새 파일 없으면 기존 이미지 유지
	            board.setImg(existingImg);
	        }
			
			boardService.updateBoard(board);
			return new ResponseEntity<>("게시글이 수정되었습니다.", HttpStatus.OK);
		}catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>("게시글 수정 실패",HttpStatus.INTERNAL_SERVER_ERROR);
		}
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
