package com.suntime.study.api;

import com.suntime.study.dto.BoardDTO;
import com.suntime.study.entity.Board;
import com.suntime.study.service.BoardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class BoardApiController {
    @Autowired
    private BoardService boardService;
    private BoardService articleService; // 서비스 객체 주입
    // GET
    @GetMapping("/api/board")
    public List<Board> index(){
        return boardService.index();
    }
    @GetMapping("/api/board/{id}")
    public Board show(@PathVariable Long id){ //URL의 id를 매개변수로 받아오기
        return boardService.show(id);
    }
    // POST
    @PostMapping("/api/board")
    public ResponseEntity<Board> create(@RequestBody BoardDTO dto) { // create() 메서드 정의
        Board created = boardService.create(dto); // dto를 Entity로 변환
        return (created != null) ?
                ResponseEntity.status(HttpStatus.OK).body(created) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    // PATCH
    @PatchMapping("/api/board/{id}")
    public ResponseEntity<Board> update(@PathVariable Long id,
                                        @RequestBody BoardDTO dto){
        Board updated = boardService.update(id,dto); // 서비스를 통해 게시글 수정
        return (updated != null) ? // 수정되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.OK).body(updated) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

    }
    // DELETE
    @DeleteMapping("/api/board/{id}")
    public ResponseEntity<Board> delete(@PathVariable Long id){
        Board deleted = articleService.delete(id);
        return (deleted != null) ? // 삭제가 되면 정상, 안 되면 오류 응답
                ResponseEntity.status(HttpStatus.NO_CONTENT).build() :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
    @PostMapping("/api/board/transaction-test")
    public ResponseEntity<List<Board>> transactionTest
            (@RequestBody List<BoardDTO> dtos){
        List<Board> createdList = articleService.createBoards(dtos);
        return (createdList != null) ?
                ResponseEntity.status(HttpStatus.OK).body(createdList) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
