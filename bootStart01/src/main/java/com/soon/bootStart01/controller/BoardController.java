package com.soon.bootStart01.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soon.bootStart01.entity.Board;
import com.soon.bootStart01.form.BoardForm;
import com.soon.bootStart01.form.RepleForm;
import com.soon.bootStart01.service.BoardService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor // final이 붙은 속성을 포함하는 생성자를 자동으로 생성해 줌 - 의존성 주입의 한 방법
@Controller
public class BoardController {

//	private final BoardRepository boardRepository;
	private final BoardService boardService;

	// 게시글 리스트 조회
	@GetMapping("/main")
	public String goBoardMain(Model model, @RequestParam(value = "page", defaultValue = "0") int page) {
		Page<Board> boardList = this.boardService.selectList(page);
		model.addAttribute("boardList", boardList);
		return "board_main";
	}

	// 게시글 상세 조회
	// board_detail.html에서 repleForm을 사용하기 때문에 매개변수에 repleForm을 불러와줘야한다
	@GetMapping("/detail/{boardNum}")
	public String boardDetail(
			// 게시글 조회할 때 사용할 매개변수
			Model model, @PathVariable("boardNum") Integer boardNum, RepleForm repleForm
			) {
		Board board = this.boardService.boardDetail(boardNum);
		
		this.boardService.boardViewCount(board);
		model.addAttribute(board);
		return "board_detail";
	}

	// 게시글 작성 폼으로 이동
	@GetMapping("/create")
	public String boardCreate(BoardForm boardForm) {
		return "board_form";
	}
	
	// 게시글 작성하기
	@PostMapping("/create")
	// @Valid - 지정한 Form에서 설정한 유효성 검사를 진행함(notEmpty, size...등)
	// BindingResult - Form(클래스)에서 설정한 항목과 같은 폼(태그)이 전송되면 자동으로 같은 속성이 바인딩 됨
	public String boardCreate(@Valid BoardForm boardForm, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "board_form";
		}
		this.boardService.boardCreate(boardForm.getBoardTitle(), boardForm.getBoardCon());
		return "redirect:/board/main";
	}

}
