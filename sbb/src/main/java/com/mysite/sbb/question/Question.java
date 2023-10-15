package com.mysite.sbb.question;

import java.time.LocalDateTime;
import java.util.List;

import com.mysite.sbb.answer.Answer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity // Entity 어노테이션을 적용해야 JPA가 엔티티로 인식
public class Question{
	
	// 프라이머리 키 설정
	@Id
	// GeneratedValue - 자동으로 1씩 증가하는 시퀀스
	// GenerationType.IDENTITY : 해당 컬럼만의 독립적인 시퀀스 생성
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 200)
	private String subject;
	
	@Column(columnDefinition = "TEXT")
	private String content;
	
	// createDate 속성의 실제 테이블의 컬럼명은 create_date
	// 카멜케이스 이름은 _로 단어가 구분되어 실제 테이블 컬럼명이 생성됨
	private LocalDateTime createDate;

	// 하나의 질문에 답변 여러개이기 때문에 여기서는 OneToMany를 사용하고 List형태
	// mappedBy는 참조한 속성명.
	// cascade는 질문 삭제 시 답변들도 함께 삭제시키기 위함
	@OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
	private List<Answer> answerList;
}
