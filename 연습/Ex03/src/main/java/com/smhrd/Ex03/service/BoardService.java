package com.smhrd.Ex03.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smhrd.Ex03.DataNotFoundException;
import com.smhrd.Ex03.entity.Board;
import com.smhrd.Ex03.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시판 전체 리스트 조회
	public List<Board> selectList(){		
		return this.boardRepository.findAll();
	}
	
	// 게시글 상세 조회
	public Board boardDetail(Integer boardNum) {
		Optional<Board> board = this.boardRepository.findById(boardNum);
		if(board.isPresent()) {
			return board.get();
		}else {
			throw new DataNotFoundException("entity not found");
		}		
	}
}
