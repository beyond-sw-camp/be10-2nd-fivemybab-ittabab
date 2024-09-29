package com.fivemybab.ittabab.board.command.application.controller;

import com.fivemybab.ittabab.board.command.application.dto.CreateBoardDTO;
import com.fivemybab.ittabab.board.command.application.dto.UpdatedBoardDTO;
import com.fivemybab.ittabab.board.command.application.service.BoardCommandService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardCommandController {

    private final BoardCommandService boardCommandService;


    //게시판 작성(create)
    @PostMapping
    public ResponseEntity<CreateBoardDTO> createBoard(@RequestBody CreateBoardDTO createBoardDTO, @RequestParam Long userId) {
        createBoardDTO.setCreateDate(LocalDateTime.now());
        CreateBoardDTO createdBoard = boardCommandService.createBoard(createBoardDTO, userId);
        return new ResponseEntity<>(createdBoard, HttpStatus.CREATED);
    }

    //게시판 삭제(delete)
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable final Long postId){
        boardCommandService.deleteBoard(postId);
        return ResponseEntity.noContent().build();
    }

    //게시판 수정 (update)
    @PutMapping("/{postId}")
    public ResponseEntity<Void> updateBoard(
            @PathVariable Long postId,
            @RequestBody @Valid UpdatedBoardDTO updatedBoardDTO) {

        boardCommandService.updateBoard(postId, updatedBoardDTO);
        return ResponseEntity.ok().build();
    }
}