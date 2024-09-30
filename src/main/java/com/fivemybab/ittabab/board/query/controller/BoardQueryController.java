package com.fivemybab.ittabab.board.query.controller;

import com.fivemybab.ittabab.board.query.dto.BoardQueryDto;
import com.fivemybab.ittabab.board.query.service.BoardQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board")
@RequiredArgsConstructor
@Tag(name = "Board", description = "게시판 관련 API")
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    /* 게시물 목록 조회 (최신순) */
    @Operation(summary = "게시글 목록 조회(최신순)")
    @GetMapping("/posts/time")
    public ResponseEntity<List<BoardQueryDto>> getPostsByTime() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryService.findPostsByTime();
        return ResponseEntity.ok(posts);
    }

    /* 게시물 목록 조회 (좋아요 내림차순) */
    @Operation(summary = "게시글 목록 조회(좋아요 내림차순)")
    @GetMapping("/posts/likes/desc")
    public ResponseEntity<List<BoardQueryDto>> getPostsByLikesDesc() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryService.findPostsByLikesDesc();
        return ResponseEntity.ok(posts);
    }

    /* 게시물 목록 조회 (좋아요 오름차순) */
    @Operation(summary = "게시글 목록 조회(좋아요 오름차순)")
    @GetMapping("/posts/likes/asc")
    public ResponseEntity<List<BoardQueryDto>> getPostsByLikesAsc() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryService.findPostsByLikesAsc();
        return ResponseEntity.ok(posts);
    }



/*    *//* 게시물 ID로 조회 *//*
    @GetMapping("/post/{postId}")
    public ResponseEntity<BoardQueryDto> getPostById(@PathVariable Long postId) throws NotFoundException {
        BoardQueryDto post = boardQueryService.findPostById(postId);
        return ResponseEntity.ok(post);
    }*/
}
