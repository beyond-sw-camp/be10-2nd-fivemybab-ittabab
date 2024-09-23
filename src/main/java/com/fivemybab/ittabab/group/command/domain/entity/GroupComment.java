package com.fivemybab.ittabab.group.command.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_comment_id")
public class GroupComment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupCommentId;
    private Long groupId;
    private Long parentCommentId;
    private Long userId;
    private String commentContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;
}
