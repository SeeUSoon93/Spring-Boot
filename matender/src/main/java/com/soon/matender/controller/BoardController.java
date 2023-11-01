package com.soon.matender.controller;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.soon.matender.service.BoardService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/board")
@RequiredArgsConstructor // final이 붙은 속성을 포함하는 생성자를 자동으로 생성해 줌 - 의존성 주입의 한 방법
@Controller
public class BoardController {
	private final BoardService boardService;
	
}
