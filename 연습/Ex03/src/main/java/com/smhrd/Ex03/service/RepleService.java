package com.smhrd.Ex03.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.smhrd.Ex03.entity.Board;
import com.smhrd.Ex03.entity.Reple;
import com.smhrd.Ex03.repository.RepleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RepleService {

	private final RepleRepository repleRepository;
	
	// 댓글 달기 기능
	public void createReple(Board board, String repleCon) {
		Reple reple = new Reple();
		reple.setRepleCon(repleCon);
		reple.setRepleDate(LocalDateTime.now());
		reple.setBoard(board);
		this.repleRepository.save(reple);
	}
}
