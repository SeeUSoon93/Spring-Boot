package com.soon.Ex02.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.soon.Ex02.Entity.Board;

public interface BoardRepository extends JpaRepository<Board, Integer> {

}
