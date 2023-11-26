package com.soon.Ex02.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soon.Ex02.Entity.Board;
import com.soon.Ex02.Service.BoardService;
import com.soon.Ex02.Service.RepleService;

import ch.qos.logback.core.model.Model;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class RepleController {
	
	private final BoardService boardService;
	private final RepleService repleService;
	
	// 댓글 다는 기능
	// @RequestParam - 컨트롤러 메서드에서 HTTP 요청의 매개변수를 추출
	// 				   주로 GET, POST 요청에서 URL 매개변수, 요청 매개변수를 추출하는데 사용
	@PostMapping("/reple/create/{boardNum}")
	public String createReple(@PathVariable("boardNum")Integer boardNum, @RequestParam String repleCon) {
		Board board = this.boardService.selectBoard(boardNum);
		this.repleService.createReple(board, repleCon);
		return "redirect:/board/detail/"+boardNum;
	}

}
