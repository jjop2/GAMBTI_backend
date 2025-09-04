package com.example.team3.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.team3.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	List<Board> findAllByOrderByIdDesc();
	
	List<Board> findByUserIdOrderByIdDesc(Integer userId);
	
}
