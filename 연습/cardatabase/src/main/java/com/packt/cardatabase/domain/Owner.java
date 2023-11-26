package com.packt.cardatabase.domain;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long ownerid;
	private String firstname, lastname;
	
	
	public Owner(String firstname, String lastname) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
	}


	/*
	 * // casecade 특성은 삭제 또는 업데이트 시 연속 효과가 적용되는 방법을 지정 // mappedBy 특성은 Car클래스에 있는
	 * owner필드가 이 관계의 기본 키임을 지정함
	 * 
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner") private List<Car>
	 * cars;
	 */
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "car_owner",
				joinColumns = {@JoinColumn(name="ownerid")},
				inverseJoinColumns = {@JoinColumn(name="`id`")})
	private Set<Car> cars = new HashSet<Car>();
		
}
