package com.fivemybab.ittabab.group.command.domain.aggregate;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_comment")
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

    public void modifyCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public void modifyUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
