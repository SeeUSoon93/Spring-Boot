package com.smhrd.Ex03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smhrd.Ex03.entity.Board;
import com.smhrd.Ex03.service.BoardService;
import com.smhrd.Ex03.service.RepleService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reple")
@RequiredArgsConstructor
@Controller
public class RepleController {
	
	private final RepleService repleService;
	private final BoardService boardService;
	
	// 댓글 다는 기능
	@PostMapping("/create/{boardNum}")
	public String createReple(@PathVariable("boardNum") Integer boardNum, @RequestParam String repleCon) {
		Board board = this.boardService.boardDetail(boardNum);
		this.repleService.createReple(board, repleCon);
		return String.format("redirect:/board/detail/%s", boardNum);		
	}
	

}
