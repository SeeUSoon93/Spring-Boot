package com.yse.dev.book.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookLog {
    // 책 로그의 고유 ID
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookLogId;

    // 책 로그와 연결된 책 (지연 로딩)
    // 책 로그와 책 간의 다대일(1:N) 관계
    // fetch 속성은 연관된 엔티티를 지연 로딩하도록 설정
    // @JoinColumn 어노테이션은 외래 키(Foreign Key)를 매핑하는 데 사용
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    // 책 로그에 대한 코멘트 (TEXT 형식)
    // 엔티티의 필드와 데이터베이스 테이블의 컬럼 간의 매핑을 정의
    // columnDefinition 속성은 comment 필드가 TEXT 형식으로 저장됨 의미
    @Column(columnDefinition = "TEXT")
    private String comment;

    // 책 로그의 페이지 정보
    private Integer page;

    // 책 로그의 생성 일시
    @CreationTimestamp
    private LocalDateTime insertDateTime;
}
