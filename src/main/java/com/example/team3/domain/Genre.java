package com.example.team3.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity

@JsonIgnoreProperties(ignoreUnknown = true)
public class Genre {
	@Id
    private Integer id;
	
	@Column
    private String description;
	
	private String name;

}
