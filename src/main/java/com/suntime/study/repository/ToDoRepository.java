package com.suntime.study.repository;

import com.suntime.study.entity.ToDoEntity;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDoEntity,Integer> {
    List<ToDoEntity> findAllByEmail(String userEmail);
}
