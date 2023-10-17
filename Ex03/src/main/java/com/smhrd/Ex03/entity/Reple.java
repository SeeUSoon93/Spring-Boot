package com.smhrd.Ex03.entity;

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
	
	@Column(columnDefinition = "TEXT")
	private String repleCon;
	
	private LocalDateTime repleDate;
	
	@ManyToOne	
	private Board board;

}
