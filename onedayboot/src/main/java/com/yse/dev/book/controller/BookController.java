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
	
	// 책 정보가 없을 때 NoSuchElementException 오류 발생
	// 매번 try-catch로 잡기보다 특정 오류가 날 경우 자동으로 특정 메소드 실행되도록 어노테이션
	
	/*
	 * @ExceptionHandler(NoSuchElementException.class) public ModelAndView
	 * noSuchElementExceptionHandler(NoSuchElementException ex) { ModelAndView mav =
	 * new ModelAndView(); mav.setStatus(HttpStatus.UNPROCESSABLE_ENTITY);
	 * mav.addObject("message", "책 정보가 없습니다."); mav.addObject("location",
	 * "/book/list"); mav.setViewName("common/error/422"); return mav; }
	 */
	
	// 같은 역할을 하는 로직이 두군데로 분산되기 때문에 기존것을 수정해 아래의 error422 메소드를 이용
	@ExceptionHandler(NoSuchElementException.class)  
	public ModelAndView noSuchElementExceptionHandler(NoSuchElementException ex) {  
	    return this.error422("책 정보가 없습니다.", "/book/list");  
	}  
		
	// 유효성 검사가 실패할 경우(책 수정 시 오류 처리)
	// 위와는 다르게 보여질 message와 이동할 페이지만을 매개변수로 받음
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
	@PostMapping("/book/create")
	public String insert(BookCreateDTO bookCreateDTO) {
		Integer bookId = this.bookService.insert(bookCreateDTO);
		return String.format("redirect:/book/read/%s", bookId);
	}
	
	// 책 정보 조회 기능
	@GetMapping("/book/read/{bookId}")
	public ModelAndView read(@PathVariable Integer bookId) {
		ModelAndView mav = new ModelAndView(); 
		try {
			BookReadResponseDTO bookReadResponseDTO = this.bookService.read(bookId);
			mav.addObject("bookReadResponseDTO",bookReadResponseDTO);
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
	// 위의 read와는 달리 @ExceptionHandler를 사용해 에러 제어
	@GetMapping("/book/edit/{bookId}")  
	public ModelAndView edit(@PathVariable Integer bookId) throws NoSuchElementException {  
	    ModelAndView mav = new ModelAndView();  
	    BookEditResponseDTO bookEditResponseDTO = this.bookService.edit(bookId);  
	    mav.addObject("bookEditResponseDTO", bookEditResponseDTO);  
	    mav.setViewName("book/edit");  
	    return mav;  
	}  
	
	// 책 수정 요청을 처리할 메소드
	@PostMapping("/book/edit/{bookId}")
	public ModelAndView update(@Validated BookEditDTO bookEditDTO, Errors errors) {
		if(errors.hasErrors()) {
			String errorMessage = errors
								  .getFieldErrors()
								  .stream()
								  .map(x -> x.getField() + " : " + x.getDefaultMessage())
								  .collect(Collectors.joining("\n"));
			return this.error422(errorMessage, String.format("/book/edit/%s", bookEditDTO.getBookId()));
					
		}
		this.bookService.update(bookEditDTO);
		
		
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName(String.format("redirect:/book/read/%s", bookEditDTO.getBookId()));		
		return mav;
	}
	
	// 책 삭제 요청을 처리할 메소드
	@PostMapping("/book/delete")  
	public String delete(Integer bookId) throws NoSuchElementException{  
	    this.bookService.delete(bookId);  
	    return "redirect:/book/list";  
	}  

	// 책 목록 메소드 
	// 모든 로직이 서비스에서 처리되기 떄문에 모델 경로 지정, 서비스 호출 후 뷰 반환
	@GetMapping(value = {"/book/list","/book"})
	public ModelAndView bookList(String title, Integer page, ModelAndView mav) {
		mav.setViewName("/book/list");
		
		List<BookListResponseDTO> books = this.bookService.bookList(title, page);
		mav.addObject("books",books);
		return mav;
	}
	
	
	
	
	
	
	
	
	
	
	
	

	
}
