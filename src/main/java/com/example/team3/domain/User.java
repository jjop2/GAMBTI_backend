package com.example.team3.domain;



import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable= false , unique = true, length=20)
	private String username;
	
	@Column(nullable= false , length=20)
	private String password;
	
	@Column(nullable= false , unique = true)
	private String email;
	
	@CreationTimestamp
	private Timestamp Date;
	
}
