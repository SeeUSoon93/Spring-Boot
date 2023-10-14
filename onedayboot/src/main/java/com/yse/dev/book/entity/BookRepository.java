package com.yse.dev.book.entity;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    // JpaRepository를 확장한 BookRepository 인터페이스

    // 제목을 포함하는 도서 목록 검색
    public List<Book> findByTitleContains(String title, Pageable pageable);
    // findByTitleContains: 제목을 포함하는 도서 목록을 검색하기 위한 메서드
    // title: 검색할 도서 제목
    // Pageable: 페이징 및 정렬 정보를 처리하기 위한 Spring Data 클래스

    // JpaRepository의 기본 메서드들은 이 인터페이스에 이미 포함되어 있음
}
