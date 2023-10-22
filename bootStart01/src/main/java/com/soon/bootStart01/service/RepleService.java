package com.soon.bootStart01.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.soon.bootStart01.entity.Board;
import com.soon.bootStart01.entity.Reple;
import com.soon.bootStart01.repository.RepleRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RepleService {
	
	private final RepleRepository repleRepository;
	
	public void repleCreate(Board board, String repleCon) {
		Reple r = new Reple();
		r.setRepleCon(repleCon);
		r.setBoard(board);
		r.setRepleDate(LocalDateTime.now());
		this.repleRepository.save(r);
	}
	
	
	
	
	
	
	

}
