package com.yse.dev.book.dto;

import java.time.LocalDateTime;

import com.yse.dev.book.entity.Book;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class BookReadResponseDTO {  
	 private Integer bookId;  
	 private String title;  
	 private Integer price;  
	 private LocalDateTime insertDateTime;  

	 public BookReadResponseDTO fromBook(Book book) {  
	 this.bookId = book.getBookId();  
	 this.title = book.getTitle();  
	 this.price = book.getPrice();  
	 this.insertDateTime = book.getInsertDateTime();  
	 return this;  
	 }  

	 public static BookReadResponseDTO BookFactory(Book book) {  
	 BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();  
	 bookReadResponseDTO.fromBook(book);  
	 return bookReadResponseDTO;  
	 }  
	}  
