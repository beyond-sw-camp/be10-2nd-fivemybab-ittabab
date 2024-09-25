package com.fivemybab.ittabab.board.command.application.controller;

import com.fivemybab.ittabab.board.command.application.dto.CreateBoardDTO;
import com.fivemybab.ittabab.board.command.application.dto.UpdatedBoardDTO;
import com.fivemybab.ittabab.board.command.application.service.BoardCommandService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardCommandController {

    private BoardCommandService boardCommandService;

    public BoardCommandController(BoardCommandService boardCommandService) {this.boardCommandService = boardCommandService;}

    //게시판 작성(create)
    @PostMapping("/create")
    public ResponseEntity<CreateBoardDTO> createBoard(@RequestBody CreateBoardDTO createBoardDTO, @RequestParam Long userId) {
        createBoardDTO.setCreateDate(LocalDateTime.now());
        boardCommandService.createBoard(createBoardDTO, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //게시판 삭제(delete)
    @DeleteMapping("/delete/{postId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable final Long postId){
        boardCommandService.deleteBoard(postId);
        return ResponseEntity.noContent().build();
    }

    //게시판 수정 (update)
    @PutMapping("/update/{postId}") //https://velog.io/@yjw8459/PostMapping%EA%B3%BC-PutMapping%EC%9D%98-%EC%B0%A8%EC%9D%B4%EC%A0%90
    public ResponseEntity<Void> updateBoard(
            @PathVariable Long postId,
            @RequestPart @Valid UpdatedBoardDTO updatedBoardDTO) {
        boardCommandService.updateBoard(postId, updatedBoardDTO);
        return ResponseEntity.ok().build();
    }
}
