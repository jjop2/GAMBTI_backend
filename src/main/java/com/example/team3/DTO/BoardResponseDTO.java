package com.example.team3.DTO;

import com.example.team3.domain.Board;

import lombok.Data;

@Data
public class BoardResponseDTO {
	private Integer id;
	private String title;
	private String content;
	private String img;
	private String writerName;
	
	public BoardResponseDTO(Board board) {
		this.id = board.getId();
		this.title = board.getTitle();
		this.content = board.getContent();
		this.img = board.getImg();
		this.writerName = board.getUser().getNickname();
	}
	
	
}
