package com.suntime.study.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
// Entity란 실제 DB에 매칭되는 클래스를 의미한다
@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

    @Column(unique = true, nullable = false, length = 30)
    private String memberEmail;

    @Column(nullable = false, length = 255)
    private String memberPW;

    @Column(nullable = false, length = 10)
    private String memberName;

    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private int authority = 0;

    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private int memberEmailCheck = 0;

    @Column(nullable = false)
    private String verificationToken;

    // 생성자 추가
    public MemberEntity(String memberEmail, String memberPW, String memberName, String verificationToken) {
        this.memberEmail = memberEmail;
        this.memberPW = memberPW;
        this.memberName = memberName;
        this.verificationToken = verificationToken;
    }

    // 기본 생성자
    public MemberEntity() {
    }

    // toMemberEntity 메서드는 필요하지 않음
}