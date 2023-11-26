package com.yse.dev.book.service;

import org.springframework.stereotype.Service;

import com.yse.dev.book.dto.BookLogCreateDTO;
import com.yse.dev.book.dto.BookLogCreateResponseDTO;
import com.yse.dev.book.entity.Book;
import com.yse.dev.book.entity.BookLog;
import com.yse.dev.book.entity.BookLogRepository;
import com.yse.dev.book.entity.BookRepository;

@Service
public class BookLogService {
    private BookRepository bookRepository;  // 도서 (Book) 관련 데이터 액세스를 위한 리포지토리
    private BookLogRepository bookLogRepository;  // 도서 로그 (BookLog) 관련 데이터 액세스를 위한 리포지토리

    // 생성자를 통한 의존성 주입
    public BookLogService(BookRepository bookRepository, BookLogRepository bookLogRepository) {
        this.bookRepository = bookRepository;
        this.bookLogRepository = bookLogRepository;
    }

    // 도서 로그 (BookLog) 생성 메서드
    public BookLogCreateResponseDTO insert(BookLogCreateDTO bookLogCreateDTO) {
        // 주어진 도서 ID를 사용하여 도서를 검색하고, 존재하지 않을 경우 예외를 발생시킴
        Book book = this.bookRepository.findById(bookLogCreateDTO.getBookId()).orElseThrow();

        // 도서 로그 (BookLog) 엔티티를 생성
        BookLog bookLog = BookLog.builder()
            .book(book)
            .comment(bookLogCreateDTO.getComment())
            .page(bookLogCreateDTO.getPage())
            .build();

        // 도서 로그 (BookLog)를 저장하고, 저장된 엔티티를 반환
        bookLog = this.bookLogRepository.save(bookLog);

        // 도서 로그 응답 DTO를 생성하고 반환
        return BookLogCreateResponseDTO.BookLogFactory(bookLog);
    }
}
