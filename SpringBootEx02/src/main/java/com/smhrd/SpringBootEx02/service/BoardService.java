package com.smhrd.SpringBootEx02.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.smhrd.SpringBootEx02.entity.Board;
import com.smhrd.SpringBootEx02.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	public List<Board> getList(){
		return this.boardRepository.findAll();
	}
	
	public Board getBoard(Integer boardNum) {
		Optional<Board> board = this.boardRepository.findById(boardNum);
		if(board.isPresent()) {
			return board.get();
		}else {
			throw new DataNotFounException("해당 게시글을 찾지 못했습니다.");
		}
		
	}
	
	public void create(String boardTitle, String boardCon) {
		Board board = new Board();
		board.set
		board.setContent(content);
		board.setBoardDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}

}
