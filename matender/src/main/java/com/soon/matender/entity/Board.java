package com.soon.matender.entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long boardIdx;
	
	private String boardTitle;
	
	private String boardCategory;
	
	private LocalDateTime boardDate;
	
	@Column(columnDefinition = "default 0")
	private Long boardGood;
	
	private String boardText;
	
	private User user;

}
