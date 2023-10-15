package com.mysite.sbb.question;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 서비스에는 똑같이 @Service를 붙여줘야함 
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
	// 전체 목록 조회
	public List<Question> getList(){
		return this.questionRepository.findAll();
	}
	
	// 게시글 상세 조회
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.questionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		}else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	
	
	
	
	
}
