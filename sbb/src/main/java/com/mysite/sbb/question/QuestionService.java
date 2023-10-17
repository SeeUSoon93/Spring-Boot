package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.mysite.sbb.DataNotFoundException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service // 서비스에는 똑같이 @Service를 붙여줘야함 
public class QuestionService {
	
	private final QuestionRepository questionRepository;
	
//	// 전체 목록 조회
//	public List<Question> getList(){
//		return this.questionRepository.findAll();
	
	// 전체 목록 페이징
	public Page<Question> getList(int page){
		// page는 조회할 페이지의 번호, 10은 한 페이지에 보여줄 게시물의 갯수
		Pageable pageable = PageRequest.of(page, 10);
		return this.questionRepository.findAll(pageable);
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
	
	// 게시글 작성
	public void create(String subject, String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		this.questionRepository.save(question);
	}
	
}
