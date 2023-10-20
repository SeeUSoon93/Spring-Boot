package com.soon.Ex02.Service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.soon.Ex02.Entity.Board;
import com.soon.Ex02.Entity.Reple;
import com.soon.Ex02.Repository.RepleRepository;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

// 서비스가 필요한 이유 - 컨트롤러에서 레파지토리의 의존성을 바로 주입받는 경우,
// 					   해당 메소드가 필요할때마다 새로 생성해야 함.
//                     중복 생성을 막고 캡슐화를 위해 레파지토리->서비스->컨트롤러의 과정을 거침
//                     보안상 이유 : 만약 컨트롤러가 해킹당하면 레파지토리까지 접근가능
//                                 그러나, 서비스가 중간에 있음으로 컨트롤러가 해킹당해도 레파지토리에 접근불가능 

// 서비스는 레파지토리를 주입받고, 컨트롤러는 서비스를 주입받음
// 쉽게 말하면 - 레파지토리에 있는 메서드를 서비스에서 사용하기 편하기 위함
// 마찬가지로 서비스를 거쳐 컨트롤러에서도 사용하기 위해 서비스를 주입받음
@RequiredArgsConstructor
@Service
public class RepleService {
	
	private final RepleRepository repleRepository;
	
	// 댓글 다는 메서드
	public void createReple(Board board, String repleCon) {
		Reple reple = new Reple();
		reple.setRepleCon(repleCon);
		reple.setRepleDate(LocalDateTime.now());
		reple.setBoard(board);
		this.repleRepository.save(reple);
	}
	
	
	
	
	
	
	

}
