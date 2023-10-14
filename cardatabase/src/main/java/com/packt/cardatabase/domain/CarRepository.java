package com.packt.cardatabase.domain;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

// CrudRepository를 확장하는 PagingAndSortingRepository가 있음
// 이를 통해 페이지 매김과 정렬을 적용하고 엔티티를 검색하는 메소드를 제공함
public interface CarRepository extends CrudRepository<Car, Long>{
	// <Car, Long>는 이것이 Car 엔티티 클래스의 리포지터리이고 ID필드의 형식이 Long임을 정의함

	// long count() - 엔티티의 수를 반환
	// Iterable<T> findAll() - 지정한 형식의 모든 항목을 반환
	// Optional<T> findById(ID id) -  지정한 ID의 한 항목을 반환
	// void delete(T entity) - 엔티티 삭제
	// void delete All - 리포지터리의 모든 엔티티 삭제
	// <S extends T> save(S entity) - 엔티티를 저장
	// List<S> saveAll(Iterable<S> entities) - 여러 엔티티를 저장

	
	// 브랜드로 자동차를 검색
	// OrderBy 키워드로 쿼리 정렬
	List<Car> findByBrandOrderByYearAsc(String brand);
	
	// 색상으로 자동차를 검색
	List<Car> findByColor(String color);
	
	// 연도로 자동차를 검색
	List<Car> findByYear(int year);
	
	// 브랜드와 모델로 자동차를 검색(And사용)
	List<Car> findByBrandAndModel(String brand, String model);
	
	// 브랜드나 색상으로 자동차를 검색(Or사용)
	List<Car> findByBrandOrColor(String brand, String color);
	
	// SQL문을 사용해 브랜드로 자동차를 검색
	// @Query 어노테이션에서는 like 같은 고급 식을 지정가능
	@Query("select c from Car c where c.brand like %?1")
	List<Car> findByBrand(String brand);

	// PagingAndSortingRepository를 사용하면 두개의 추가 메소드를 사용 가능
	// Iterable<T> findAll(Sort sort) - 지정한 옵션으로 정렬된 모든 엔티티를 반환
	// Page<T> finaAll(Pageable pageable) - 지정한 페이지 매김 옵션으로 모든 엔티티를 반환
	
	
}


