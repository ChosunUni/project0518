package com.suntime.study.controller;

import com.suntime.study.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailVerificationController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/verify-email")
    public String verifyEmail(@RequestParam("email") String email, @RequestParam("token") String token, Model model) {
        // 이메일 확인 작업 수행 (member_table의 member_email_check 값을 1로 업데이트)
        boolean isEmailVerified = memberService.verifyEmail(email, token);

        // 이메일 확인 결과에 따라 페이지 결정
        if (isEmailVerified) {
            model.addAttribute("message", "이메일이 성공적으로 확인되었습니다. 로그인 해주십시오.");
            return "verification_success"; // 인증 성공 페이지로 이동
        } else {
            model.addAttribute("message", "이메일 확인에 실패하였습니다. 다시 시도해주십시오.");
            return "verification_failed"; // 인증 실패 페이지로 이동
        }
    }
}
