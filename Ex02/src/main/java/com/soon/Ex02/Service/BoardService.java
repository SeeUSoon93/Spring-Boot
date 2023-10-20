package com.soon.Ex02.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.soon.Ex02.DataIsNull;
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
	
	// 게시글 상세조회
	public Board selectBoard(Integer boardNum) {
		// Optional 타입으로 받아야 함!
		// Optional - null처리를 유연하게 하기 위해 사용하는 클래스
		// 			  isPresent로 null인지 아닌지를 확인한 후
		//            실제 객체값을 받음
		// findById - id는 프라이머리키를 의미(프라이머리키로 조회)
		Optional<Board> board = this.boardRepository.findById(boardNum);
		if(board.isPresent()) {
			// 널이 아님!
			return board.get();
			// 현재 board는 Optional객체 - 실제 객체 내용은 get()을 통해 가져옴
		}else {
			throw new DataIsNull("없는 게시글");
		}
	}
	
	
	
	
	
	
	
	

}
