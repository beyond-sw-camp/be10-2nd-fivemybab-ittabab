package com.fivemybab.ittabab.board.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PostCommentQueryDto {
    private Long postId;        // 게시물 ID
    private String title;       // 게시물 제목
    private String content;     // 게시물 내용
    private int commentCount;   // 댓글 수
    private String memberName;  // 게시물 작성자 이름
    private String createdAt;   // 생성일
}
