package com.mysite.sbb;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;
import com.mysite.sbb.question.QuestionService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/question")
// RequiredArgsConstructor - final이 붙은 속성을 포함하는 생성자를 자동으로 생성
@RequiredArgsConstructor
@Controller
public class QuestionController {
	
//	private final QuestionRepository questionRepository;
	// 레파지토리 대신 서비스를 사용하도록 수정
	private final QuestionService questionService;

	// 목록 조회
	@GetMapping("/list")
    public String list(Model model) {
        List<Question> questionList = this.questionService.getList();
        model.addAttribute("questionList", questionList);
        return "question_list";
	}
	
	// 상세 조회
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute("question", question);
		return "question_detail";
	}
	
	
	
	
	
}
