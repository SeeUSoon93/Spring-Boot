package com.yse.dev.book.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
//이 클래스가 JPA 엔티티임을 나타냄
//JPA (Java Persistence API)는 Java 언어로 데이터베이스와 상호 작용하기 위한 API를 제공
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Book") // 이 클래스가 데이터베이스의 "Book" 테이블과 매핑됨을 나타냅니다.
public class Book {
    // 책의 고유 ID
    @Id
    // bookId 필드의 값을 자동으로 생성하기 위해 사용
    // 시퀀스(generator)를 통해 값을 할당하며, allocationSize는 시퀀스로부터 한 번에 가져올 값의 개수를 설정
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookId_seq")
    @SequenceGenerator(name = "bookId_seq", sequenceName = "bookId_seq", allocationSize = 1)
    @Column(name = "bookId")
    private Integer bookId;

    // 책의 제목 (최대 200자)
    @Column(length = 200)
    private String title;

    // 책의 가격
    private Integer price;

    // 책의 생성 일시
    @CreationTimestamp // insertDateTime 필드를 엔티티가 데이터베이스에 저장될 때 현재 일시로 자동 설정합니다.
    private LocalDateTime insertDateTime;

    // 책과 연결된 책 로그 목록 (지연 로딩)
    // 책과 책 로그 간의 일대다(1:N) 관계를 나타냄
    // mappedBy 속성은 연관된 엔티티인 BookLog 엔티티의 book 필드에 의해 관리된다는 것을 의미
    // fetch 속성은 연관된 엔티티를 지연 로딩하도록 설정
    // @Builder.Default 어노테이션을 사용하여 비어 있는 목록을 생성
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @Builder.Default
    private List<BookLog> bookLogList = new ArrayList();
}
