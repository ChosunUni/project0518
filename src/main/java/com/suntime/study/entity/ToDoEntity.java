package com.suntime.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "todo_table")
public class ToDoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idx;

    @Column
    private String email;

    @Column(length = 200)
    private String content;

    @Column(nullable = false, columnDefinition = "INTEGER DEFAULT 0")
    private Integer completed;
}