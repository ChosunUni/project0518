package com.suntime.study.dto;

import com.suntime.study.entity.MemberEntity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MemberDTO {

    @Id // pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto_increment
    private int idx;

    @NotNull
    @Email
    @Pattern(regexp = "^[\\w._%+-]+@[\\w.-]+\\.[a-zA-Z]{2,6}$", message = "이메일 형식으로 작성해주세요")
    @Column(unique = true, nullable = false, length = 30)
    private String memberEmail;

    @NotNull(message = "패스워드는 필수 입력 항목입니다.")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[!@#$%^&*()-_+=]).{8,20}$", message = "대문자, 소문자, 숫자, 특수 기호가 각각 하나 이상 포함되어야 하며, 8자리 이상 20자리 이하여야 합니다.")
    @Column(nullable = false, length = 255)
    private String memberPW;

    @NotNull(message = "이름은 필수 입력 항목입니다.")
    @Pattern(regexp = "^.{3,10}$", message = "이름은 3글자에서 10글자 사이여야 합니다.")
    @Column(nullable = false, length = 10)
    private String memberName;

    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private int authority;

    @NotNull
    @Column(nullable = false, columnDefinition = "int(1) default 0")
    private int memberEmailCheck;

    @Column(nullable = false)
    private String verificationToken; // 추가: 회원 인증에 사용되는 토큰 필드

    public static MemberDTO toMemberDTO(MemberEntity memberEntity){
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setIdx((int) memberEntity.getIdx());
        memberDTO.setMemberEmail(memberEntity.getMemberEmail());
        memberDTO.setMemberPW(memberEntity.getMemberPW());
        memberDTO.setMemberName(memberEntity.getMemberName());
        memberDTO.setAuthority(memberEntity.getAuthority());
        memberDTO.setMemberEmailCheck(memberEntity.getMemberEmailCheck());
        memberDTO.setVerificationToken(memberEntity.getVerificationToken());
        return memberDTO;
    }
}
