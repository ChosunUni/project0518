package com.suntime.study.repository;

import com.suntime.study.entity.TimerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TimerRepository extends JpaRepository<TimerEntity, Long> {
    List<TimerEntity> findAllByEmail(String userEmail);
}
