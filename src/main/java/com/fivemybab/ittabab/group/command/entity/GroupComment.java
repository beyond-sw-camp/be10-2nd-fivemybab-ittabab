package com.fivemybab.ittabab.group.command.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "group_comment_id")
public class GroupComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupCommentId;
    private int groupId;
    private String commentContent;
    private Date createDate;
    private Date updateDate;
    private int memberId;
    private boolean isBlinded;
}
