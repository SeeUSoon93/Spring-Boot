package com.mysite.sbb.question;

import org.springframework.data.jpa.repository.JpaRepository;

// 리포지터리 - 엔티티에 의해 생성된 데이터베이스 테이블에 접근하는 메서드들을 사용하기 위한 인터페이스
// 데이터 처리에는 CRUD가 필요함. 이 CRUD를 어떻게 처리할지 정의하는 계층

// JpaRepository를 상속할 때는 대상이 되는 엔티티의 타입(Question)과 해당 엔티티의 PK속성타입(Integer)를 지정해야함
public interface QuestionRepository extends JpaRepository<Question, Integer>{

	// finBySubject와 같은 메서드는 기본 제공이 되지 않기 떄문에 인터페이스에 적어줘야함
	Question findBySubject(String subject);
	
	Question findBySubjectAndContent(String subject, String content);
}


// And - findBySubjectAndContent(String subject, String content) : 여러 컬럼을 and로 검색
// Or - findBySubjectOrContent(String subject, String content) : 여러 컬럼을 or로 검색
// Between - findByCreateDateBetween(LocalDateTime fromDate, LocalDateTime toDate) : 컬럼을 between으로 검색
// lessThan - findByIdLessThan(Integer id) : 작은 항목 검색
// GreaterThanEqual - findByIdGraterThanEqual(Integer id) : 크거나 같은 항목 검색
// Like - findBySubjectLike(String subject) : like 검색(특정 문자열이 포함되어 있는 데이터)
// In - findBySubjectIn(String[] subjects) : 여러 값중에 하나인 항목 검색
// OrderBy - findBySubjectOrderByCreateDateAsc(String subject) : 검색 결과를 정렬하여 전달
// !! 응답결과가 여러건인 경우 메서드의 리턴 타입을 Question이 아닌 List<Question>으로