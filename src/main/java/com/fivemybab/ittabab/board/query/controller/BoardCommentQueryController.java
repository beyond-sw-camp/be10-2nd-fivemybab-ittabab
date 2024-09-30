package com.fivemybab.ittabab.board.query.controller;

import com.fivemybab.ittabab.board.query.dto.BoardCommentQueryDto;
import com.fivemybab.ittabab.board.query.service.BoardCommentQueryService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/boardComment")
@RequiredArgsConstructor
public class BoardCommentQueryController {

    private final BoardCommentQueryService boardCommentQueryService;

    /* 댓글 많은 순으로 게시글 목록 조회 */
    @Operation(summary = "댓글 많은 순 게시글 목록")
    @GetMapping("/posts/comments")
    public ResponseEntity<List<BoardCommentQueryDto>> getPostsByCommentCount() throws NotFoundException {
        List<BoardCommentQueryDto> posts = boardCommentQueryService.findPostsByCommentCount();
        return ResponseEntity.ok(posts);
    }
}
