package com.fivemybab.ittabab.board.command.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePostCommentDto {
    private Long postId;       // 댓글이 달릴 게시글 ID
    private Long userId;       // 댓글 작성자 ID
    private String commentContent; // 댓글 내용
}