package com.soon.myhome.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.soon.myhome.entity.Board;
import com.soon.myhome.entity.Reple;
import com.soon.myhome.repository.RepleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RepleService {

	private final RepleRepository repleRepository;
	
	public void createReple(Board board, String repleCon) {
		Reple reple = new Reple();
		reple.setRepleCon(repleCon);
		reple.setRepleDate(LocalDateTime.now());
		reple.setBoard(board);
		this.repleRepository.save(reple);
	}
	
	
}
