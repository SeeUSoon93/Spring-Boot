package com.soon.bootStart01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soon.bootStart01.entity.Board;
import com.soon.bootStart01.service.BoardService;
import com.soon.bootStart01.service.RepleService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/reple")
@Controller
@RequiredArgsConstructor
public class RepleController {
	
	 private final RepleService repleService;
	 private final BoardService boardService;
	 
	 // 댓글 
	 @PostMapping("/create/{boardNum}")
	 public String repleCreate(@PathVariable("boardNum")Integer boardNum, @RequestParam String repleCon) {
		 Board board = this.boardService.boardDetail(boardNum);
		 this.repleService.repleCreate(board, repleCon);
		 return "redirect:/board/detail/"+boardNum;
	 }
	 
	 
	
}
