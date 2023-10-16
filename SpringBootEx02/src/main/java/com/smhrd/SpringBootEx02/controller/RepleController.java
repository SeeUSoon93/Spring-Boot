package com.smhrd.SpringBootEx02.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.SpringBootEx02.entity.Board;
import com.smhrd.SpringBootEx02.form.RepleForm;
import com.smhrd.SpringBootEx02.service.BoardService;
import com.smhrd.SpringBootEx02.service.RepleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/reple")
@RequiredArgsConstructor
@Controller
public class RepleController {

	private final BoardService boardService;
	private final RepleService repleService;
	
	@PostMapping("/create/{id}")
	public String createAnswer(Model model, @PathVariable("boardNum") Integer boardNum,
			@Valid RepleForm repleForm, BindingResult bindingResult) {
		Board board = this.boardService.getBoard(boardNum);
		if(bindingResult.hasErrors()) {
			model.addAttribute("board", board);
			return "question_detail";
		}	
		this.repleService.create(board, repleForm.getRepleCon());
		return String.format("redirect:/question/detail/%s", boardNum);
	}
	
	
}
