package com.fivemybab.ittabab.board.query.controller;

import com.fivemybab.ittabab.board.query.dto.PostCommentQueryDto;
import com.fivemybab.ittabab.board.query.service.PostCommentQueryService;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/postComment")
@RequiredArgsConstructor
public class PostCommentQueryController {

    private final PostCommentQueryService postCommentQueryService;

    /* 댓글 많은 순으로 게시글 목록 조회 */
    @GetMapping("/posts/comments")
    public ResponseEntity<List<PostCommentQueryDto>> getPostsByCommentCount() throws NotFoundException {
        List<PostCommentQueryDto> posts = postCommentQueryService.findPostsByCommentCount();
        return ResponseEntity.ok(posts);
    }
}
