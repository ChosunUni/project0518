package com.suntime.study.controller;

import com.suntime.study.dto.BoardDTO;
import com.suntime.study.dto.CommentDto;
import com.suntime.study.entity.Board;
import com.suntime.study.repository.BoardRepository;
import com.suntime.study.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class BoardController {//
    @Autowired // 스프링 부트가 미리 생성해 놓은 리파지터리 객체 주입(DI)
    private BoardRepository boardRepository;
    @Autowired
    private CommentService commentService;

    @GetMapping("/board/write")
    public String newBoardForm(){
        return "board/write";
    }
    @PostMapping("/board/create")
    public String createBoard(BoardDTO boardDTO){
        log.info(boardDTO.toString());
//        System.out.println(form.toString());
        // 1. DTO를 엔티티로 변환
        Board board = boardDTO.toEntity();
        log.info(board.toString());
//        System.out.println(board.toString());
        // 2. 리파지터리로 엔티티를 DB에 저장
        Board saved = boardRepository.save(board);
        log.info(saved.toString());
//        System.out.println(saved.toString());
        return "redirect:/board/" + saved.getId(); // 리다이렉트를 작성할 위치 git
    }
    @GetMapping("/board/{id}") // 데이터 조회 요청 접수
    public String show(@PathVariable Long id, Model model){ // 매개변수로 id 받아 오기
        log.info("id = " + id);
        // 1. id를 조회해 데이터 가져오기
        Board boardEntity = boardRepository.findById(id).orElse(null); // 이는 id 값으로 데이터를 찾을 때 해당 id 값이 없으면 null을 반환하라는 뜻입니다.
        List<CommentDto> commentsDtos = commentService.comments(id);
        // 2. 모델에 데이터 등록하기
        model.addAttribute("board",boardEntity); // 변수값을 "변수명"이라는 이름으로 추가
        model.addAttribute("commentDtos",commentsDtos);
        // 3. 뷰 페이지 반환하기
        return "board/show";
    }
    @GetMapping("/board")
    public String index(Model model){ // model 객체 받아오기
        // 1. 모든 데이터 가져오기
        ArrayList<Board> boardEntityList = boardRepository.findAll();
        // 2. 모델에 데이터 등록하기
        model.addAttribute("boardList",boardEntityList);
        // 3. 뷰 페이지 설정하기
        return "board/index";
    }
    @GetMapping("/board/{id}/edit")
    public String edit(@PathVariable Long id,Model model){
        // 수정할 데이터 가져오기
        Board boardEntity = boardRepository.findById(id).orElse(null);
        // 모델에 데이터 등록하기
        model.addAttribute("board",boardEntity);
        // 뷰 페이지 설정하기 //
        return "board/edit";
    }
    @PostMapping("/board/update")
    public String update(BoardDTO boardDTO){
        log.info(boardDTO.toString());
        // 1.DTO를 엔티티로 변환하기
        Board boardEntity = boardDTO.toEntity();
        log.info(boardEntity.toString());
        // 2.엔티티를 DB에 저장하기
        // 2-1. DB에서 기존 데이터 가져오기
        Board target = boardRepository.findById(boardEntity.getId()).orElse(null);
        // 2-2. 기존 데이터 값을 갱신하기
        if(target != null){
            boardRepository.save(boardEntity); // 엔티티를 DB에 저장(갱신)
        }
        // 3.수정 결과 페이지로 리다이렉트하기
        return "redirect:/board/" + boardEntity.getId();
    }
    @GetMapping("/board/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes rttr){
        log.info("삭제 요청이 들어왔습니다!!");
        // 1.삭제할 대상 가져오기
        Board target = boardRepository.findById(id).orElse(null);
        log.info(target.toString());
        // 2.대상 엔티티 삭제하기
        if(target != null){
            boardRepository.delete(target);
            rttr.addFlashAttribute("msg","삭제됐습니다!");
        }
        // 3.결과 페이지로 리다이렉트하기
        return "redirect:/board";
    }

}
