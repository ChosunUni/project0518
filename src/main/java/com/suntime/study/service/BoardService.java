package com.suntime.study.service;

import com.suntime.study.dto.BoardDTO;
import com.suntime.study.entity.Board;
import com.suntime.study.repository.BoardRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    public List<Board> index() {
        return boardRepository.findAll();
    }

    public Board show(Long id) {
        return boardRepository.findById(id).orElse(null);
    }

    public Board create(BoardDTO dto) {
        Board board = dto.toEntity();
        if(board.getId() != null){
            return null;
        }
        return boardRepository.save(board);
    }

    public Board update(Long id, BoardDTO dto) {
        // 1. DTO -> 엔티티 변환하기
        Board board = dto.toEntity(); // dto를 entity로 변환
        // log.info("id: {}, board: {}", id,board.toString());
        // 2. 타킷 조회하기
        Board target = boardRepository.findById(id).orElse(null);
        // 3. 잘못된 요청 처리하기
        if(target == null || id != board.getId()){
            // 400, 잘못된 요청 응답!
            // log.info("잘못된 요청! id:{}, board:{}",id,board.toString());
            return null;
        }
        // 4. 업데이트하기
        target.patch(board);
        Board updated = boardRepository.save(target); // article 엔티티 DB에 저장
        return updated; // 정상 응답
    }

    public Board delete(Long id) {
        // 1. 대상찾기
        Board target = boardRepository.findById(id).orElse(null);
        // 2. 잘못된 요청 처리하기
        if(target == null){
            return null;
        }
        // 3. 대상 삭제하기
        boardRepository.delete(target);
        return target;
    }

    @Transactional
    public List<Board> createBoards(List<BoardDTO> dtos) {
        // 1. dto 묶음을 엔티티 묶음으로 변환하기
        List<Board> boardList = dtos.stream()
                .map(dto -> dto.toEntity())
                .collect(Collectors.toList());
        // 2. 엔티티 묶음을 DB에 저장하기
        boardList.stream()
                .forEach(board -> boardRepository.save(board));
        // 3. 강제 예외 발생시키기
        boardRepository.findById(-1L)
                .orElseThrow(()-> new IllegalArgumentException("실패!"));
        // 결과 값 반환하기
        return boardList;
    }
}
