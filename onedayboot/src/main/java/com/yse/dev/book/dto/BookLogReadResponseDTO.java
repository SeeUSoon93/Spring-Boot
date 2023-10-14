package com.yse.dev.book.dto;

import java.time.LocalDateTime;

import com.yse.dev.book.entity.BookLog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class BookLogReadResponseDTO {
    // 책 로그의 고유 ID
    private Integer bookLogId;

    // 책 로그에 대한 코멘트 또는 설명
    private String comment;

    // 읽은 페이지 수
    private Integer page;

    // 책 로그의 생성 일시
    private LocalDateTime insertDateTime;

    // 화면에 표시되는 코멘트
    private String displayComment;

    // BookLog 엔티티의 정보를 이 DTO 객체로 변환하는 메소드
    public BookLogReadResponseDTO fromBookLog(BookLog bookLog) {
        this.bookLogId = bookLog.getBookLogId();
        this.comment = bookLog.getComment();
        this.page = bookLog.getPage();
        this.insertDateTime = bookLog.getInsertDateTime();

        // displayComment: 페이지 정보와 코멘트를 조합하여 화면에 표시할 코멘트 생성
        this.displayComment = (this.page == null ? "" : "(p." + String.valueOf(this.page) + ".) ") + this.comment;

        return this;
    }

    // BookLogReadResponseDTO 객체를 생성하고 BookLog 엔티티 정보로 초기화하는 정적 팩토리 메소드
    public static BookLogReadResponseDTO BookLogFactory(BookLog bookLog) {
        BookLogReadResponseDTO bookLogReadResponseDTO = new BookLogReadResponseDTO();
        bookLogReadResponseDTO.fromBookLog(bookLog);
        return bookLogReadResponseDTO;
    }
}
