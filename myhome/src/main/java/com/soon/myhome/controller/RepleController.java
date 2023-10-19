package com.soon.myhome.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.soon.myhome.entity.Board;
import com.soon.myhome.form.RepleForm;
import com.soon.myhome.service.BoardService;
import com.soon.myhome.service.RepleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/reple")
@RequiredArgsConstructor
@Controller
public class RepleController {

	private final BoardService boardService;
	private final RepleService repleService;

	@PostMapping("/create/{boardNum}")
	public String createAnswer(Model model, @PathVariable("boardNum") Integer boardNum,

			@Valid RepleForm repleForm, BindingResult bindingResult) {
		Board board = this.boardService.getBoard(boardNum);
		if (bindingResult.hasErrors()) {
			model.addAttribute("board", board);
			return "board_detail";
		}
		this.repleService.createReple(board, repleForm.getRepleCon());
		return String.format("redirect:/board/detail/%s", boardNum);
	}

}
