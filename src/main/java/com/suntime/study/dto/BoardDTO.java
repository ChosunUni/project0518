package com.suntime.study.dto;

import com.suntime.study.entity.Board;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class BoardDTO {
    private Long id;
    private String title; // 제목을 받을 필드
    private String content; // 내용을 받을 필드

    public Board toEntity() {
        return new Board(id, title, content); // Board 엔티티의 ID는 자동으로 생성되므로 null로 설정
    }
}
