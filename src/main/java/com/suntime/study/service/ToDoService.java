package com.suntime.study.service;

import java.util.List;

import com.suntime.study.entity.ToDoEntity;
import com.suntime.study.repository.ToDoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ToDoService {
    private final ToDoRepository toDoRepository;

    public List<ToDoEntity> subAllByEmail(String userEmail) {
        return toDoRepository.findAllByEmail(userEmail);
    }

    public void create(String email ,String content) {
        ToDoEntity toDoEntity = new ToDoEntity();
        toDoEntity.setEmail(email);
        toDoEntity.setContent(content);
        toDoEntity.setCompleted(0); // completed 필드를 0으로 설정
        this.toDoRepository.save(toDoEntity);
    }

    public void delete(Integer idx){
        ToDoEntity toDoEntity = toDoRepository.findById(idx)
                .orElseThrow(()->new IllegalArgumentException("해당 아이템이 없습니다. idx=" + idx));
        this.toDoRepository.delete(toDoEntity);
    }

    public void update(Integer idx, String content){
        ToDoEntity toDoEntity = toDoRepository.findById(idx)
                .orElseThrow(()->new IllegalArgumentException("해당 아이템이 없습니다. idx=" + idx));
        toDoEntity.setContent(content);
        this.toDoRepository.save(toDoEntity);
    }

    public void changeStatus(Integer idx, Integer newStatus) {
        ToDoEntity toDoEntity = toDoRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 아이템이 없습니다. idx=" + idx));
        toDoEntity.setCompleted(newStatus);
        this.toDoRepository.save(toDoEntity);
    }

    public ToDoEntity findById(Integer idx) {
        return toDoRepository.findById(idx)
                .orElse(null);
    }
}
