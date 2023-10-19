package com.soon.myhome.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soon.myhome.exception;
import com.soon.myhome.entity.Board;
import com.soon.myhome.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 서비스에는 똑같이 @Service를 붙여줘야함 
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시판 전체 조회
	//public List<Board> selectList(){
	//	return this.boardRepository.findAll(); 
	//}
	
	// 전체 목록 페이징
	public Page<Board> getList(int page){
		// 최근에 작성한 게시글이 가장먼저 보이게
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("boardDate"));
		
		// page는 조회할 페이지의 번호, 10은 한 페이지에 보여줄 게시물의 갯수
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sorts));
		return this.boardRepository.findAll(pageable);
	}
	
	// 게시글 상세 조회
	public Board getBoard(Integer id) {
		Optional<Board> board = this.boardRepository.findById(id);
		if(board.isPresent()) {
			return board.get();
		}else {
			throw new exception("board not found");
		}
	}
	
	// 게시글 작성
	public void createBoard(String boardTitle, String boardCon) {
		Board board = new Board();
		board.setBoardTitle(boardTitle);
		board.setBoardCon(boardCon);
		board.setBoardDate(LocalDateTime.now());
		this.boardRepository.save(board);
	}
	
	
	
	
	
	

}
