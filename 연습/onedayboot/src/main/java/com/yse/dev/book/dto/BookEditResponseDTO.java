package com.yse.dev.book.dto;

import java.time.LocalDateTime;

import com.yse.dev.book.entity.Book;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
// 파일을 복사할 때 내용도 입력한 클래스명에 맞게 참조를 쫒아가 다시 설정 해줌
// 단 변수명은 바뀌지 않기 때문에 직접 수정(문제는 없지만 다른 사람이 이해하는데 어려움이 있을 수 있음)

@Data
@NoArgsConstructor
@Getter
public class BookEditResponseDTO {  
	 private Integer bookId;  
	 private String title;  
	 private Integer price;  
	 private LocalDateTime insertDateTime;  
	 
	 // Book 엔티티 객체에서 정보를 추출하여 BookEditResponseDTO 객체에 할당하는 역할	 
	 public BookEditResponseDTO fromBook(Book book) {  
	 this.bookId = book.getBookId();  
	 this.title = book.getTitle();  
	 this.price = book.getPrice();  
	 this.insertDateTime = book.getInsertDateTime();  
	 return this;  
	 }  
	 
	 // 이 메소드는 Book 엔티티 정보로 초기화된 BookEditResponseDTO 객체를 생성하는 정적 팩토리 메소드
	 // 이를 통해 BookEditResponseDTO 객체를 간단하게 생성하고 초기화
	 public static BookEditResponseDTO BookFactory(Book book) {  
	 BookEditResponseDTO bookEditResponseDTO = new BookEditResponseDTO();  
	 bookEditResponseDTO.fromBook(book);  
	 return bookEditResponseDTO;   
	 }  
	}  
