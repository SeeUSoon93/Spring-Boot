package com.smhrd.Ex03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.Ex03.entity.Board;
import java.util.List;


public interface BoardRepository extends JpaRepository<Board, Integer>{
	
	Board findByBoardTitle(String boardTitle);
	
	
}
