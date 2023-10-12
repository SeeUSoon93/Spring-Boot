package com.yse.dev.book.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Book")
public class Book {  
		
	  @Id
	  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookId_seq")
	  @SequenceGenerator(name = "bookId_seq", sequenceName = "bookId_seq", allocationSize = 1)
	  @Column(name = "bookId")
	  private Integer bookId; 

	  @Column(length = 200)  
	  private String title;  

	  private Integer price;  

	  @CreationTimestamp  
	  private LocalDateTime insertDateTime;  
	}  
