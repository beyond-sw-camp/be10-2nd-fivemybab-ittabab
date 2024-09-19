package com.fivemybab.ittabab.group.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class GroupCommentDTO {
    private int groupCommentId;
    private int groupId;
    private String commentContent;
    private Date createDate;
    private Date updateDate;
    private int memberId;
}
