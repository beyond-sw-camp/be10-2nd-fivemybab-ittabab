package com.fivemybab.ittabab.board.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "post_comment")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PostComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postCommentId;
    private int postId;
    private int parentCommentId;
    private int memberId;
    private String commentContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;
}