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
public class GroupInfoDTO {
    private int groupId;
    private int groupCounting;
    private int groupStatus;
    private Date createDate;
    private Date endDate;
    private String groupPost;
    private int memberId;
    private int groupCategoryId;
}
