package com.fivemybab.ittabab.board.query.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.fivemybab.ittabab.board.query.dto.PostQueryDto;
import com.fivemybab.ittabab.board.query.service.PostQueryService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Tag(name = "Post", description = "게시판 관련 API")
public class PostQueryController {

    private final PostQueryService postQueryService;

    /* 게시물 목록 조회 (최신순) */
    @Operation(summary = "게시글 목록 조회(최신순)", description = "게시판의 글을 최신순으로 조회합니다.")
    @GetMapping("/recent")
    public ResponseEntity<List<PostQueryDto>> getPostsByTime(Authentication loginId) throws NotFoundException {
        List<PostQueryDto> posts = postQueryService.findPostsByTime(loginId);
        return ResponseEntity.ok(posts);
    }

    /* 게시물 목록 조회 (좋아요 내림차순) */
    @Operation(summary = "게시글 목록 조회(좋아요 내림차순)", description = "게시판의 글을 좋아요 내림차순으로 조회합니다.")
    @GetMapping("/likes/desc")
    public ResponseEntity<List<PostQueryDto>> getPostsByLikesDesc(Authentication loginId) throws NotFoundException {
        List<PostQueryDto> posts = postQueryService.findPostsByLikesDesc(loginId);
        return ResponseEntity.ok(posts);
    }

    /* 게시물 목록 조회 (좋아요 오름차순) */
    @Operation(summary = "게시글 목록 조회(좋아요 오름차순)", description = "게시판의 글을 좋아요 오름차순으로 조회합니다.")
    @GetMapping("/likes/asc")
    public ResponseEntity<List<PostQueryDto>> getPostsByLikesAsc(Authentication loginId) throws NotFoundException {
        List<PostQueryDto> posts = postQueryService.findPostsByLikesAsc(loginId);
        return ResponseEntity.ok(posts);
    }
}
