package com.yse.dev.book.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yse.dev.book.dto.BookLogCreateDTO;
import com.yse.dev.book.dto.BookLogCreateResponseDTO;
import com.yse.dev.book.service.BookLogService;

//@RestController 어노테이션: 이 컨트롤러는 RESTful 웹 서비스를 처리하는 데 사용됩니다. JSON 또는 XML과 같은 데이터 형식을 반환합니다.
@RestController
@RequestMapping("/book-log")
public class BookLogController {

	// BookLogService 인스턴스를 컨트롤러 내에서 사용하기 위한 필드를 선언합니다.
	private BookLogService bookLogService;
	
	//  Spring의 의존성 주입(DI)을 사용하여 BookLogService를 주입합니다.
	@Autowired
	public void setBookLogService(BookLogService bookLogService) {
		this.bookLogService = bookLogService;
	}
	

	@PostMapping("/create")
	public ResponseEntity<BookLogCreateResponseDTO> insert(@RequestBody BookLogCreateDTO bookLogCreateDTO) {
		// BookLogService를 이용해 BookLogCreateDTO를 새로운 책 로그로 생성하고
		// 그 결과를 BookLogCreateResponseDTO에 저장합니다.
		BookLogCreateResponseDTO bookLogCreateResponseDTO = this.bookLogService.insert(bookLogCreateDTO);

		// ResponseEntity를 사용하여 HTTP 응답을 생성하고,
		// HTTP 상태 코드를 "ok" (200)로 설정하고 생성된 데이터를 클라이언트에 반환합니다.
		return ResponseEntity.ok(bookLogCreateResponseDTO);
	}
}
