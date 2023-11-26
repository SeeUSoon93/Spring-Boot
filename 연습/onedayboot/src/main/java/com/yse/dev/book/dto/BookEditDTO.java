package com.yse.dev.book.dto;

import com.yse.dev.book.entity.Book;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
public class BookEditDTO {
    
    // 책 ID. 반드시 양수여야 합니다.
    @NonNull
    @Positive
    private Integer bookId;
    
    // 책 제목. 비어있지 않아야 합니다.
    @NonNull
    @NotBlank
    private String title;
    
    // 책 가격. 최소값은 1000 이상이어야 합니다.
    @NonNull
    @Min(1000)
    private Integer price;

    // BookEditDTO 객체의 내용을 Book 엔티티로 채우는 메소드
    public Book fill(Book book) {
        // Book 엔티티의 제목과 가격을 BookEditDTO의 값으로 설정하고 반환합니다.
        book.setTitle(this.title);
        book.setPrice(this.price);
        return book;
    }
}
