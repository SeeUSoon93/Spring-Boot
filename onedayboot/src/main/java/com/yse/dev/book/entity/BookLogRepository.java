package com.yse.dev.book.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookLogRepository extends JpaRepository<BookLog, Integer> {  

	
	// BookLogRepository 인터페이스는 Spring Data JPA의 JpaRepository<BookLog, Integer>를 확장
	// JpaRepository는 Spring Data JPA에서 제공하는 인터페이스로, 엔티티와 관련된 데이터베이스 작업을 수행하기 위한 메소드를 제공
	// BookLog는 엔티티 유형을 나타냅니다. 이것은 BookLog 엔티티와 관련된 데이터베이스 테이블에 대응합니다.
	// Integer은 엔티티의 주요 키(primary key)의 데이터 유형을 지정합니다.
	// BookLog 엔티티의 bookLogId 필드가 주요 키(primary key)로 사용되며, 이 필드는 정수형(Integer)입니다.
	// 이 인터페이스를 사용하면 Spring Data JPA가 자동으로 엔티티와 관련된 데이터베이스 작업을 수행하는 메소드를 생성
	// 이를 통해 데이터베이스에서 엔티티를 저장, 조회, 수정, 삭제하는 작업을 쉽게 수행할 수 있습니다.
}  