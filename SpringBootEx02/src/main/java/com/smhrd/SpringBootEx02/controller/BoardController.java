package com.smhrd.SpringBootEx02.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.smhrd.SpringBootEx02.entity.Board;
import com.smhrd.SpringBootEx02.form.BoardForm;
import com.smhrd.SpringBootEx02.form.RepleForm;
import com.smhrd.SpringBootEx02.service.BoardService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
//RequiredArgsConstructor - final이 붙은 속성을 포함하는 생성자를 자동으로 생성
@RequiredArgsConstructor
@Controller
public class BoardController {

	private final BoardService boardService;
	
	@GetMapping("/list")
    public String list(Model model) {
        List<Board> boardList = this.boardService.getList();
        model.addAttribute("boardList", boardList);
        return "board_list";
	}

	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id, RepleForm repleForm) {
		Board board = this.boardService.getboard(id);
		model.addAttribute("board", board);
		return "board_detail";
	}
	
	@GetMapping("/create")
	public String boardCreate(BoardForm boardForm) {
		return "board_form";
	}
	
	@PostMapping("/create")
	public String boardCreate(@Valid BoardForm boardForm, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "board_form";
		}		
		this.boardService.create(boardForm.getTitle(), boardForm.getBoardCon());
		return "redirect:/board/list";
	}
	
	
	
	
	
}
