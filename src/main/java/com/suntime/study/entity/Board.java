package com.suntime.study.entity;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Getter
@Setter
@Table(name = "board_table")
public class Board {
    @Id // 엔티티의 대푯값 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private Long id;
    @Column // title 필드 선언, DB 테이블의 title 열과 연결됨
    private String title;
    @Column // content 필드 선언, DB 테이블의 content 열과 연결됨
    private String content;
    public Long getId(){
        return id;
    }

    public void patch(Board board) {
        if(board.title != null)
            this.title = board.title;
        if(board.content != null)
            this.content = board.content;
    }
}
