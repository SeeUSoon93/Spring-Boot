package com.packt.cardatabase.domain;

import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO) // 데이터베이스가 자동으로 ID를 생성하도록 지정(AUTO는 가장 적합하게 선택한다는 의미)
	private long Id;	
	private String brand, model, color, registerNumber;	
	@Column(name = "explanation", nullable=false, length=512)
	private int year;	
	private int price;
	

	
	public Car(String brand, String model, String color, String registerNumber, int year, int price, Set<Owner> owners) {
		super();
		this.brand = brand;
		this.model = model;
		this.color = color;
		this.registerNumber = registerNumber;
		this.year = year;
		this.price = price;
		this.owners = owners;
	}



	/*
	 * // 일대다 관계 추가하는 어노테이션 // FetchType은 데이터베이스에서 데이터를 검색하는 전략을 정의
	 * 
	 * @ManyToOne(fetch = FetchType.EAGER)
	 * 
	 * @JoinColumn(name="owner") private Owner owner;
	 */
	
	// 일대다 관계 추가하는 어노테이션
	// FetchType은 데이터베이스에서 데이터를 검색하는 전략을 정의
	@ManyToMany(mappedBy = "cars")
	private Set<Owner> owners = new HashSet<Owner>();
	
	
}
