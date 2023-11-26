package com.smhrd.Ex03.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.smhrd.Ex03.entity.Board;
import com.smhrd.Ex03.repository.BoardRepository;
import com.smhrd.Ex03.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor
@Controller
public class BoardController {
	
	private final BoardService boardService;
	
	// 게시판 메인화면으로 이동
	@GetMapping("/")
	public String boardHome(Model model) {
		List<Board> boardList = this.boardService.selectList();
		model.addAttribute(boardList);
		return "board_home";
	}

	// 게시글 상세조회
	@GetMapping("/detail/{boardNum}")
	public String boardDetail(Model model, @PathVariable("boardNum") Integer boardNum) {
		Board board = this.boardService.boardDetail(boardNum);
		model.addAttribute(board);
		return "board_detail";
	}
	
	
	
}
