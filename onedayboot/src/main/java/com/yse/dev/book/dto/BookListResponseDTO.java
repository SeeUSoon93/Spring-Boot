package com.yse.dev.book.dto;

import lombok.Getter;

@Getter
public class BookListResponseDTO {
    
    // 책의 고유한 식별자(ID)
    private Integer bookId;
    
    // 책의 제목
    private String title;
    
    // 생성자: BookListResponseDTO 객체를 초기화하기 위한 생성자
    public BookListResponseDTO(Integer bookId, String title) {
        this.bookId = bookId;
        this.title = title;
    }
}
