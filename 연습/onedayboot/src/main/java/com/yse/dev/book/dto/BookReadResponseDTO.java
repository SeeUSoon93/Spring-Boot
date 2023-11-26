package com.yse.dev.book.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.yse.dev.book.entity.Book;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Getter
public class BookReadResponseDTO {
    // 책의 고유 ID
    private Integer bookId;

    // 책의 제목
    private String title;

    // 책의 가격
    private Integer price;

    // 책의 생성 일시
    private LocalDateTime insertDateTime;

    // 책에 대한 로그 목록
    private List<BookLogReadResponseDTO> bookLogs;

    // Book 엔티티의 정보를 이 DTO 객체로 변환하는 메소드
    public BookReadResponseDTO fromBook(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.price = book.getPrice();
        this.insertDateTime = book.getInsertDateTime();

        // 책에 대한 로그 목록을 변환하여 할당
        this.bookLogs = book.getBookLogList().stream().map(bookLog ->
                BookLogReadResponseDTO.BookLogFactory(bookLog)).collect(Collectors.toList());

        return this;
    }

    // BookReadResponseDTO 객체를 생성하고 Book 엔티티 정보로 초기화하는 정적 팩토리 메소드
    public static BookReadResponseDTO BookFactory(Book book) {
        BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();
        bookReadResponseDTO.fromBook(book);
        return bookReadResponseDTO;
    }
}
