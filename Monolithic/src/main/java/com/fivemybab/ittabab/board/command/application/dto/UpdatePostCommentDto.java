package com.fivemybab.ittabab.board.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdatePostCommentDto {
    private Long commentId;    // 수정할 댓글 ID
    private String commentContent; // 수정할 댓글 내용
}