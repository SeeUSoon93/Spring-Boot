package com.soon.Ex02.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.soon.Ex02.Entity.Board;
import com.soon.Ex02.Repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시판 전체 조회
	public List<Board> selectList(){
		return this.boardRepository.findAll(); 
	}
	
	
	
	
	
	
	
	

}
