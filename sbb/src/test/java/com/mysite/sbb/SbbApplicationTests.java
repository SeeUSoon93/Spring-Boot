package com.mysite.sbb;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mysite.sbb.answer.Answer;
import com.mysite.sbb.answer.AnswerRepository;
import com.mysite.sbb.question.Question;
import com.mysite.sbb.question.QuestionRepository;

import jakarta.transaction.Transactional;

// @SpringBootTest - SbbApplicationTests 클래스가 스프링부트 테스트 클래스임을 의미
@SpringBootTest
class SbbApplicationTests {
	
	// 스프링의 DI 기능으로 questionRepository 객체를 스프링이 자동으로 생성
	@Autowired
	private QuestionRepository questionRepository;
	
	@Autowired
	private AnswerRepository answerRepository;
	
	@Transactional //아래의 조회 후 DB세션이 끊겨 발생하는 에러를 막기 위함 - 메서드가 종료될 때까지 세션이 유지됨
	@Test
	void testJpa() {
//		//데이터 넣는 테스트
//		Question q1 = new Question(); q1.setSubject("sbb가 뭐냐?");
//		q1.setContent("sbb가 뭔데 씹덕아"); q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		 
//		Question q2 = new Question(); q2.setSubject("오늘 소주 빨 사람");
//		q2.setContent("일단 나부터ㅋㅋ"); q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
		
		
//		// 데이터 조회 테스트
//		List<Question> all = this.questionRepository.findAll();
//		// assertEquals(기대값, 실제값) - 기대값과 실제값이 동일하지를 조사. 동일하지 않으면 테스트 실패. 
//		assertEquals(2, all.size());
//		
//		Question q = all.get(0);
//		assertEquals("sbb가 뭐냐?", q.getSubject());	
		
//		// id값으로 데이터 조회 테스트
//		// finById의 리턴값은 Optional - null처리를 유연하게 처리하기 위한 클래스
//		// isPresent로 null인지 확인한 후 get으로 실제 Question객체 값을 얻어야 함
//		Optional<Question> oq = this.questionRepository.findById(1);
//        if(oq.isPresent()) {
//            Question q = oq.get();
//            assertEquals("sbb가 뭐냐?", q.getSubject());
//        }
		
//      // 제목으로 데이터 조회 테스트
//		// findBySubject와 같은 기능은 제공하지 않기 떄문에 레파지토리 인터페이스에 작성해줘야 사용가능함
//		Question q = this.questionRepository.findBySubject("sbb가 뭐냐?");
//      assertEquals(1, q.getId());
        
//		// 제목과 내용을 함께 조회
//        Question q = this.questionRepository.findBySubjectAndContent("sbb가 뭐냐?", "sbb가 뭔데 씹덕아");
//        assertEquals(1, q.getId());
		
//		// 데이터 수정 테스트
//        Optional<Question> oq = this.questionRepository.findById(1);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//        q.setSubject("수정된 제목");
//        this.questionRepository.save(q);	
		
		// 데이터 삭제 테스트
		// 삭제전에 2개가 맞는지 확인
//        assertEquals(2, this.questionRepository.count());
//        Optional<Question> oq = this.questionRepository.findById(2);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//        this.questionRepository.delete(q);
//        // 삭제후 데이터가 1개인지 확인
//        assertEquals(1, this.questionRepository.count()); 
		
		
        ////////////////////////////////////////////////////////////////
		// 답변 데이터 생성 후 저장 테스트
//		Optional<Question> oq = this.questionRepository.findById(1);
//        assertTrue(oq.isPresent());
//        Question q = oq.get();
//
//        Answer a = new Answer();
//        a.setContent("나도 몰라 씹덕아ㅋㅋ");
//        a.setQuestion(q);  // 어떤 질문의 답변인지 알기위해서 Question 객체가 필요하다.
//        a.setCreateDate(LocalDateTime.now());
//        this.answerRepository.save(a);
        
		// 답변에 연결된 질문 찾기 vs 질문에 달린 답변 찾기
        Optional<Question> oq = this.questionRepository.findById(1);
        assertTrue(oq.isPresent());
        Question q = oq.get();
        
        // 에러가 발생하는데 여기서 finById로 조회하고 나면 DB세션이 끊어지기 때문에 아래의 getAnswerList는 에러발생
        // 그러나 이 문제는 테스트 코드에서만 발생함.
        List<Answer> answerList = q.getAnswerList();

        assertEquals(1, answerList.size());
        assertEquals("나도 몰라 씹덕아ㅋㅋ", answerList.get(0).getContent());
		
        
        
        
        
		
	}
	// 테스트할 떄는 로컬서버를 중지하고 테스트해야함
}
