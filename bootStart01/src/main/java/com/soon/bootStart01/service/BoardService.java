package com.soon.bootStart01.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.soon.bootStart01.DataNotFound;
import com.soon.bootStart01.entity.Board;
import com.soon.bootStart01.entity.Reple;
import com.soon.bootStart01.repository.BoardRepository;

import lombok.RequiredArgsConstructor;

// Controller와 Repository의 중간단계 - 캡슐화를 위해서 필요함
// 보안상의 이유로 필요
@RequiredArgsConstructor
@Service 
public class BoardService {
	
	private final BoardRepository boardRepository;
	
	// 게시판 리스트 조회
	public Page<Board> selectList(int page){
		// Sort - 데이터 정렬을 지원하는 클래스
		List<Sort.Order> sort = new ArrayList<>();
		sort.add(Sort.Order.desc("boardDate"));
		Pageable pageable = PageRequest.of(page, 10, Sort.by(sort));
		// PageRequest.of(page, size, sort) - Pageable이 가진 클래스
		return this.boardRepository.findAll(pageable);
	}
	
	// 게시글 상세 조회
	public Board boardDetail(Integer boardNum) {
		/* Optional 타입으로 받아야함!
		 * Optional - null처리를 유연하게 하기 위해 사용하는 클래스
		 * 			- isPresent를 사용해서 null인지 아닌지 확인한 후
		 * 			  실체 객체값을 받음 */
		Optional<Board> b = this.boardRepository.findById(boardNum);
		if(b.isPresent()) {
			// null이 아님
			return b.get();
		}else {
			throw new DataNotFound("없는 게시글입니다.");
		}
	}
	
	// 게시글 작성
	public void boardCreate(String boardtitle, String boardCon) {
		Board b = new Board();
		b.setBoardTitle(boardtitle);
		b.setBoardCon(boardCon);
		b.setBoardDate(LocalDateTime.now());
		this.boardRepository.save(b);
		
	}
	
	
	
	
	
	
	
	

}
