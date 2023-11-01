package com.soon.matender.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Cocktail {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cockIdx;
	
	private String cockCode;
	
	private String cockName;

	private Long cockAlc;
	
	private String cockBase;
	
	private String cockInfo;
	
	private String cockRec;
	
	private User user;

}
