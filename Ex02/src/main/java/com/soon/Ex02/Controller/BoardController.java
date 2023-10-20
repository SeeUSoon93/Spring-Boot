package com.soon.Ex02.Controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.soon.Ex02.Entity.Board;
import com.soon.Ex02.Service.BoardService;

import lombok.RequiredArgsConstructor;

// RequiredArgsConstructor - final이 붙은 속성을 포함하는 생성자를 자동으로 생성해 줌
@RequiredArgsConstructor
@Controller
public class BoardController {

	// 의존성 주입
	// private final BoardRepository boardRepository;
	private final BoardService boardService;

	@GetMapping("/board")
	public String boardHome(Model model) {
		// findAll - 테이블의 모든 데이터를 조회
		List<Board> boardList = this.boardService.selectList();
		model.addAttribute(boardList);
		return "board_home";
	}

	// 게시글 상세조회
	@GetMapping("/board/detail/{boardNum}")
	public String boardDetail(Model model, @PathVariable("boardNum") Integer boardNum) {
		Board board = this.boardService.selectBoard(boardNum);
		model.addAttribute(board);
		return "board_detail";
	}

	// 게시글 등록 화면으로 이동
	@GetMapping("/board/create")
	public String createBoard() {
		return "create_board";
	}
}
