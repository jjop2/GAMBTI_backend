
package com.example.team3.domain;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "game")
public class Game {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column
	private int Steam_appid;
	private String name;
	private String required_age;
	private String header_image;
	@Column(length = 2048)
	private String pc_requirements;
	private String webm;
	@Column
	private String release_date;
	private String path_thumbnail;
	private String path_full;
	
	
//	@OneToMany(fetch = FetchType.LAZY)
//	@JoinColumn(name = "genre_id", nullable = true)
//	private List<GameGenre> genre;
	
	
}