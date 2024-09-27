package com.fivemybab.ittabab.board.query.dto;

import lombok.Data;

@Data
public class BoardQueryDto {
    private Long postId;
    private String title;
    private String content;
    private int likeCount;
    private int commentCount;
    private boolean isBlinded; // 'isVisible' 필드를 'isBlinded'로 수정
    private String createdAt;
}
