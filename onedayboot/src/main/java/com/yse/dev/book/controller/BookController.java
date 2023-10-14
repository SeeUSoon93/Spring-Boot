package com.yse.dev.book.controller;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.yse.dev.book.dto.BookCreateDTO;
import com.yse.dev.book.dto.BookEditDTO;
import com.yse.dev.book.dto.BookEditResponseDTO;
import com.yse.dev.book.dto.BookListResponseDTO;
import com.yse.dev.book.dto.BookReadResponseDTO;
import com.yse.dev.book.service.BookService;

@Controller
public class BookController {
	@Autowired
	private BookService bookService;

	// NoSuchElementException 오류를 처리하기 위한 예외 처리기
	// NoSuchElementException 예외가 발생하면, 특정 메소드를 자동으로 실행하기 위해 @ExceptionHandler
	// 어노테이션을 사용합니다.

	@ExceptionHandler(NoSuchElementException.class)
	public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {
		return this.error422("책 정보가 없습니다.", "/book/list");
	}

	// 유효성 검사 실패 시 처리를 위한 메소드
	// 이 메소드는 유효성 검사 실패 시 특정 메시지와 페이지로 리다이렉트하기 위한 메소드입니다.
	// 메시지와 리다이렉트할 위치를 받아 ModelAndView를 반환합니다.

	private ModelAndView error422(String message, String location) {
		ModelAndView mav = new ModelAndView();
		mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
		mav.addObject("message", message);
		mav.addObject("location", location);
		mav.setViewName("common/error/422");
		return mav;
	}

	@GetMapping("/book/create")
	public String create() {
		return "book/create";
	}

	// 책 입력 컨트롤러 메소드
	// 이 메소드는 POST 요청을 처리하여 새로운 책을 입력합니다.
	// BookCreateDTO를 받아서 시스템에 책을 추가합니다.

	@PostMapping("/book/create")
	public String insert(BookCreateDTO bookCreateDTO) {
		Integer bookId = this.bookService.insert(bookCreateDTO);
		return String.format("redirect:/book/read/%s", bookId);
	}

	// 책 정보 조회 기능
	// 이 메소드는 책 정보를 조회하는 GET 요청을 처리합니다.
	// bookId를 경로 변수로 받고, 책 정보를 가져오려 시도합니다. 책을 찾을 수 없으면 error422 메소드로 예외 처리합니다.

	@GetMapping("/book/read/{bookId}")
	public ModelAndView read(@PathVariable Integer bookId) {
		ModelAndView mav = new ModelAndView();
		try {
			BookReadResponseDTO bookReadResponseDTO = this.bookService.read(bookId);
			mav.addObject("bookReadResponseDTO", bookReadResponseDTO);
			mav.setViewName("book/read");
		} catch (NoSuchElementException ex) {
			mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
			mav.addObject("message", "책 정보가 없습니다.");
			mav.addObject("location", "/book");
			mav.setViewName("common/error/422");
		}
		return mav;
	}

	// 책 수정 화면
	// 이 메소드는 책 정보를 수정하기 위한 GET 요청을 처리합니다.
	// @ExceptionHandler를 사용하여 예외를 처리합니다.

	@GetMapping("/book/edit/{bookId}")
	public ModelAndView edit(@PathVariable Integer bookId) throws NoSuchElementException {
		ModelAndView mav = new ModelAndView();
		BookEditResponseDTO bookEditResponseDTO = this.bookService.edit(bookId);
		mav.addObject("bookEditResponseDTO", bookEditResponseDTO);
		mav.setViewName("book/edit");
		return mav;
	}

	// 책 수정 요청을 처리하는 메소드
	// 이 메소드는 책 정보를 업데이트하기 위한 POST 요청을 처리합니다.
	// 유효성 검사 오류가 있으면 error422 메소드를 사용하여 처리합니다.

	@PostMapping("/book/edit/{bookId}")
	public ModelAndView update(@Validated BookEditDTO bookEditDTO, Errors errors) {
		if (errors.hasErrors()) {
			String errorMessage = errors.getFieldErrors().stream()
					.map(x -> x.getField() + " : " + x.getDefaultMessage()).collect(Collectors.joining("\n"));
			return this.error422(errorMessage, String.format("/book/edit/%s", bookEditDTO.getBookId()));
		}
		this.bookService.update(bookEditDTO);

		ModelAndView mav = new ModelAndView();
		mav.setViewName(String.format("redirect:/book/read/%s", bookEditDTO.getBookId()));
		return mav;
	}

	// 책 삭제 요청을 처리하는 메소드
	// 이 메소드는 책을 삭제하기 위한 POST 요청을 처리합니다.
	// NoSuchElementException이 발생하면 @ExceptionHandler를 사용하여 예외를 처리합니다.

	@PostMapping("/book/delete")
	public String delete(Integer bookId) throws NoSuchElementException {
		this.bookService.delete(bookId);
		return "redirect:/book/list";
	}

	// 책 목록 메소드
	// 이 메소드는 책 목록을 나열하기 위한 GET 요청을 처리합니다.
	// 필터링 및 페이지네이션을 위한 선택적 매개변수를 받아서 서비스에서 책 목록을 가져온 다음, 뷰로 전달합니다.

	@GetMapping(value = { "/book/list", "/book" })
	public ModelAndView bookList(String title, Integer page, ModelAndView mav) {
		mav.setViewName("/book/list");

		List<BookListResponseDTO> books = this.bookService.bookList(title, page);
		mav.addObject("books", books);
		return mav;
	}
}
