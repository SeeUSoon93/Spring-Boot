package com.yse.dev.book.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.yse.dev.book.dto.BookCreateDTO;
import com.yse.dev.book.dto.BookEditDTO;
import com.yse.dev.book.dto.BookEditResponseDTO;
import com.yse.dev.book.dto.BookListResponseDTO;
import com.yse.dev.book.dto.BookReadResponseDTO;
import com.yse.dev.book.entity.Book;
import com.yse.dev.book.entity.BookRepository;

@Service
public class BookService {
    private BookRepository bookRepository; // 도서 (Book) 관련 데이터 액세스를 위한 리포지토리

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // 새로운 도서 생성 메서드
    public Integer insert(BookCreateDTO bookCreateDTO) {
        Book book = Book.builder()
            .title(bookCreateDTO.getTitle())
            .price(bookCreateDTO.getPrice())
            .build();

        this.bookRepository.save(book);
        return book.getBookId();
    }

    // 도서 조회 기능 메서드
    public BookReadResponseDTO read(Integer bookId) throws NoSuchElementException {
        // 주어진 도서 ID를 사용하여 도서를 검색하고, 존재하지 않을 경우 예외를 발생시킴
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        BookReadResponseDTO bookReadResponseDTO = new BookReadResponseDTO();
        bookReadResponseDTO.fromBook(book);
        return bookReadResponseDTO;
    }

    // 도서 수정 화면 컨트롤러
    public BookEditResponseDTO edit(Integer bookId) throws NoSuchElementException {
        // 주어진 도서 ID를 사용하여 도서를 검색하고, 존재하지 않을 경우 예외를 발생시킴
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        return BookEditResponseDTO.BookFactory(book);
    }

    // 도서 수정 기능 메서드
    public void update(BookEditDTO bookEditDTO) throws NoSuchElementException {
        // 주어진 도서 ID를 사용하여 도서를 검색하고, 존재하지 않을 경우 예외를 발생시킴
        Book book = this.bookRepository.findById(bookEditDTO.getBookId()).orElseThrow();
        book = bookEditDTO.fill(book);
        this.bookRepository.save(book);
    }

    // 도서 삭제 기능 메서드
    public void delete(Integer bookId) throws NoSuchElementException {
        // 주어진 도서 ID를 사용하여 도서를 검색하고, 존재하지 않을 경우 예외를 발생시킴
        Book book = this.bookRepository.findById(bookId).orElseThrow();
        this.bookRepository.delete(book);
    }

    // 도서 목록 메서드와 응답 클래스를 이용해 도서 목록을 반환하는 기능
    public List<BookListResponseDTO> bookList(String title, Integer page) {
        final int pageSize = 3;

        List<Book> books;

        if (page == null) {
            page = 0;
        } else {
            page = -1;
        }

        if (title == null) {
            Pageable pageable = PageRequest.of(page, pageSize, Direction.DESC, "insertDateTime");
            books = this.bookRepository.findAll(pageable).toList();
        } else {
            Pageable pageable = PageRequest.of(page, pageSize);
            Sort sort = Sort.by(Order.desc("insertDateTime"));
            pageable.getSort().and(sort);
            books = this.bookRepository.findByTitleContains(title, pageable);
        }

        return books.stream().map(book ->
            new BookListResponseDTO(book.getBookId(), book.getTitle())).collect(Collectors.toList());
    }
}
