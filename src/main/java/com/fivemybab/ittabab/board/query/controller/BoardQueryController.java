package com.fivemybab.ittabab.board.query.controller;

import com.fivemybab.ittabab.board.query.dto.BoardQueryDto;
import com.fivemybab.ittabab.board.query.service.BoardQueryService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/board") // Request Mapping 추가
@RequiredArgsConstructor
public class BoardQueryController {

    private final BoardQueryService boardQueryService;

    /* 모든 게시물 조회 */
    @GetMapping("/posts")
    public ResponseEntity<List<BoardQueryDto>> getAllPosts() throws NotFoundException {
        List<BoardQueryDto> posts = boardQueryService.findAllPosts();
        return ResponseEntity.ok(posts);
    }

    /* 게시물 ID로 조회 */
    @GetMapping("/posts/{postId}")
    public ResponseEntity<BoardQueryDto> getPostById(@PathVariable Long postId) throws NotFoundException {
        BoardQueryDto post = boardQueryService.findPostById(postId);
        return ResponseEntity.ok(post);
    }
}
