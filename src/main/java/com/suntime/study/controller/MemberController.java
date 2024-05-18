package com.suntime.study.controller;

import com.suntime.study.dto.MemberDTO;
import com.suntime.study.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class MemberController {
    private final MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/register")
    public String saveForm(Model model) {
        model.addAttribute("memberDTO", new MemberDTO()); // MemberDTO 객체를 모델에 추가
        return "register";
    }

    @PostMapping("/register")
    public String save(@Valid @ModelAttribute MemberDTO memberDTO, BindingResult result) {
        if (result.hasErrors()) {
            return "register"; // 유효성 검사 에러가 있으면 다시 폼으로 돌려보냄
        }
        // 회원가입 로직 수행
        memberService.save(memberDTO);
        return "redirect:/";
    }

    @PostMapping("/index")
    public String login(@ModelAttribute MemberDTO memberDTO, HttpServletRequest request, RedirectAttributes rttr) {
        MemberDTO loginResult = memberService.login(memberDTO);
        if (loginResult == null) {
            rttr.addFlashAttribute("errorMessage", "아이디 또는 비밀번호가 일치하지 않습니다.");
            return "redirect:/";
        }

        HttpSession session = request.getSession();
        session.setAttribute("loginMember", loginResult);
        return "redirect:/timer";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate(); // 세션 무효화
        }
//        sdsd
        return "redirect:/";
    }

}
