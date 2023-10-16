package com.smhrd.SpringBootEx02.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Reple {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer repleNum;
	
	private String repleCon;
	
	private LocalDateTime repleDate;	
	
	// N:1관계
	// 해당 어노테이션을 사용하여 Answer엔티티의 question속성과 Question 엔티티가 연결됨(포리너키)
	@ManyToOne
	private Board board;
	
}
