package com.soon.Ex02;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soon.Ex02.Entity.Board;
import com.soon.Ex02.Repository.BoardRepository;



@SpringBootTest
class Ex02ApplicationTests {
	
	// 의존성을 주입해줌
	@Autowired
	private BoardRepository boardRepository;
	
	// 테스트코드를 실행할때는 서버를 닫아줘야함
	// 테스트코드 실행할때는 같은 패키지에 있어야 함
	@Test
	void contextLoads() {
		Board b1 = new Board();
		b1.setBoardTitle("스프링부트가 뭐냐?");
		b1.setBoardCon("그게 뭔데");
		b1.setBoardDate(LocalDateTime.now());
		this.boardRepository.save(b1);
		
		Board b2 = new Board();
		b2.setBoardTitle("다희킴 집중하세요");
		b2.setBoardCon("훈종킴도!");
		b2.setBoardDate(LocalDateTime.now());
		this.boardRepository.save(b2);
		
	}

}
