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
import com.soon.bootStart01.repository.BoardRepository;

import jakarta.transaction.Transactional;
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
	@Transactional
	public void boardCreate(String boardtitle, String boardCon) {
		Board b = new Board();
		b.setBoardTitle(boardtitle);
		b.setBoardCon(boardCon);
		b.setBoardDate(LocalDateTime.now());
		this.boardRepository.save(b);
		
	}
	
	// 게시글 조회수 올리기
	// insert/update 둘다 save를 사용함
	// JpaRepository는 'EntityInformation' type의 entityInformation이라는 필드를 가지고 있다
	// 이 필드는 entity의 메타데이터를 가지고 있는데 얘가 이 엔티티가 새롭게 생성된 엔티티인지를 판발
	// 판별 후 기존에 있던 엔티티라면 update, 새로운 엔티티라면 insert가 실행된다.
	// 자세한 내용은 스스로...
	
	// @Transactional - 해당 메서드내에서 실행되는 모든 데이터베이스 작업을 하나의 트랜잭션으로 관리
	//					트랜잭션 내에서 예외가 발생하면 해당 메서드 실행 작업이 롤백! 데이터 관리에 용이
	@Transactional
	public void boardViewCount(Board board) {
		board.setBoardView(board.getBoardView()+1);
		this.boardRepository.save(board);
	}
	
	
	
	
	
	
	
	

}
