package com.fivemybab.ittabab.group.command.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "group_comment_id")
public class GroupComment {
    @Id
    private int groupCommentId;
    private int groupId;
    private String commentContent;
    private Date createDate;
    private Date updateDate;
    private int memberId;
    private boolean isBlinded;
}
