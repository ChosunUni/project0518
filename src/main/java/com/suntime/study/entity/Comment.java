package com.suntime.study.entity;

import com.suntime.study.dto.CommentDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY) // DB가 자동으로 1씩 증가
    private Long id; // 대표키
    @ManyToOne // Comment 엔티티와 Article 엔티티를 다대일 관계를 설정
    @JoinColumn(name = "board_id") // 외래키 생성, Article 엔티티의 기본키(id)와 매핑
    private Board board; // 해당 댓글의 부모 게시글
    @Column
    private String nickname; // 댓글을 단 사람
    @Column
    private String body; // 댓글 본문

    public static Comment createComment(CommentDto dto, Board board){
        // 예외 발생
        if(dto.getId() != null)
            throw new IllegalArgumentException("댓글 생성 실패! 댓글의 id가 없어야 합니다.");
        if(dto.getBoardId() != board.getId())
            throw new IllegalArgumentException("댓글 생성 실패! 게시글의 id가 잘못됐습니다.");
        // 엔티티 생성 및 반환
        return new Comment(
                dto.getId(), // 댓글 아이디
                board,    // 부모 게시글
                dto.getNickname(), // 댓글 닉네임
                dto.getBody() // 댓글 본문
        );
    }

    public void patch(CommentDto dto) {
        // 예외 발생
        if(this.id != dto.getId())
            throw new IllegalArgumentException("댓글 수정 실패! 잘못된 id가 입력됐습니다.");
        // 객체 갱신
        if(dto.getNickname() != null)
            this.nickname = dto.getNickname();
        if(dto.getBody() != null)
            this.body = dto.getBody();
    }
}
