package com.smhrd.Ex03;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.smhrd.Ex03.entity.Board;
import com.smhrd.Ex03.repository.BoardRepository;

import jakarta.transaction.Transactional;


@SpringBootTest
class Ex03ApplicationTests {

	@Autowired
	private BoardRepository boardRepository;
	
	@Test
	void testJpa() {
		Board b1 = new Board();
		b1.setBoardTitle("스프링부트가 뭐냐?");
		b1.setBoardCon("어떻게 하는건데?");
		b1.setBoardDate(LocalDateTime.now());
		this.boardRepository.save(b1);
		
		Board b2 = new Board();
		b2.setBoardTitle("갓순이와 일주일만에");
		b2.setBoardCon("스프링부트 끝내기!");
		b2.setBoardDate(LocalDateTime.now());
		this.boardRepository.save(b2);
	}
}
