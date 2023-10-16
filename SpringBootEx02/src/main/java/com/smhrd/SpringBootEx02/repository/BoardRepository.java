package com.smhrd.SpringBootEx02.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.smhrd.SpringBootEx02.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer>{

}
