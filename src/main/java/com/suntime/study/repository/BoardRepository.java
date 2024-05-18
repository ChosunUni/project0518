package com.suntime.study.repository;

import com.suntime.study.entity.Board;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;

public interface BoardRepository extends CrudRepository<Board,Long> {
    @Override
    ArrayList<Board> findAll();
}
