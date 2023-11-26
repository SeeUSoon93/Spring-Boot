package com.yse.dev.book.dto;

import com.yse.dev.book.entity.BookLog;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class BookLogCreateResponseDTO {
    // 생성된 책 로그의 고유 ID
    private Integer bookLogId;
    
    // 책 로그가 연결된 책의 고유 ID
    private Integer bookId;
    
    // 책 로그의 코멘트 또는 설명
    private String comment;
    
    // 읽은 페이지 수
    private Integer page;

    // BookLog 엔티티의 정보를 이 DTO 객체로 변환하는 메소드
    public BookLogCreateResponseDTO fromBookLog(BookLog bookLog) {
        this.bookLogId = bookLog.getBookLogId();
        this.bookId = bookLog.getBook().getBookId();
        this.comment = bookLog.getComment();
        this.page = bookLog.getPage();
        return this;
    }

    // BookLogCreateResponseDTO 객체를 생성하고 BookLog 엔티티 정보로 초기화하는 정적 팩토리 메소드
    public static BookLogCreateResponseDTO BookLogFactory(BookLog bookLog) {
        BookLogCreateResponseDTO bookLogCreateResponseDTO = new BookLogCreateResponseDTO();
        bookLogCreateResponseDTO.fromBookLog(bookLog);
        return bookLogCreateResponseDTO;
    }
}
