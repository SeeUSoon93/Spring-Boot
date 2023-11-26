package com.soon.bootStart01;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.soon.bootStart01.entity.Board;
import com.soon.bootStart01.repository.BoardRepository;

@SpringBootTest
class BootStart01ApplicationTests {

	@Autowired
	private BoardRepository boardRepository;
	
	// 테스트 코드를 실행할 때는 로컬서버를 중지하고 실행해야 한다
	// 테스트 코드를 실행할 때는 주입받는 파일이 같은 패키지 위치에 있어야 한다.
	
	@Test
	void contextLoads() {
		for(int i = 1; i<=100;i++) {
			Board b1 = new Board();
			b1.setBoardTitle(String.format("테스트 제목(%d)", i));
			b1.setBoardCon("테스트 내용");
			b1.setBoardDate(LocalDateTime.now());
			this.boardRepository.save(b1);
		}
	}

}
