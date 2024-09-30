package com.fivemybab.ittabab.board.command.application.controller;


import com.fivemybab.ittabab.board.command.application.dto.CreatePostCommentDto;
import com.fivemybab.ittabab.board.command.application.dto.UpdatePostCommentDto;
import com.fivemybab.ittabab.board.command.application.service.PostCommentCommandService;
import com.fivemybab.ittabab.board.command.domain.aggregate.PostComment;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post-comments")
@RequiredArgsConstructor
public class PostCommentCommandController {

    private final PostCommentCommandService postCommentCommandService;

    // 댓글 생성
    @PostMapping
    public ResponseEntity<PostComment> createComment(@RequestBody CreatePostCommentDto createPostCommentDto) {
        PostComment createdComment = postCommentCommandService.createComment(createPostCommentDto);
        return ResponseEntity.ok(createdComment);
    }

    // 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<PostComment> updateComment(@PathVariable Long commentId, @RequestBody UpdatePostCommentDto updatePostCommentDto) {
        updatePostCommentDto.setCommentId(commentId);
        PostComment updatedComment = postCommentCommandService.updateComment(updatePostCommentDto);
        return ResponseEntity.ok(updatedComment);
    }

    // 댓글 삭제 (댓글 ID 기준)
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        postCommentCommandService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }


}