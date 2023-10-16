package com.smhrd.SpringBootEx02.entity;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.Getter;

@Data
@Getter 
@Entity
public class Board {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer boardNum;
	
	@Column(length = 200)
	private String boardTitle;
	
	@Column(columnDefinition = "TEXT")
	private String boardCon;
	
	private LocalDateTime boardDate;

	@OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE)
	private List<Reple> repleList;

}
