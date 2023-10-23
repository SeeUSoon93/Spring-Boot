package com.soon.bootStart01.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soon.bootStart01.entity.Board;
import com.soon.bootStart01.form.BoardForm;
import com.soon.bootStart01.form.RepleForm;
import com.soon.bootStart01.service.BoardService;
import com.soon.bootStart01.service.RepleService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/reple")
@Controller
@RequiredArgsConstructor
public class RepleController {

	private final RepleService repleService;
	private final BoardService boardService;

	// 댓글
	@PostMapping("/create/{boardNum}")
	public String repleCreate(Model model, @PathVariable("boardNum") Integer boardNum, @Valid RepleForm repleForm,
			BindingResult bindingResult) {
		Board board = this.boardService.boardDetail(boardNum);
		if (bindingResult.hasErrors()) {
			model.addAttribute("board", board);
			return "board_detail";
		}
		this.repleService.repleCreate(board, repleForm.getRepleCon());
		return "redirect:/board/detail/" + board.getBoardNum();
	}

}
