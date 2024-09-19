package com.fivemybab.ittabab.group.command.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.Date;

@Entity
@Table(name = "group_info")
public class GroupInfo {
    @Id
    private int groupId;
    private int groupCounting;
    private int groupStatus;
    private Date createDate;
    private Date endDate;
    private String groupPost;
    private int memberId;
    private int groupCategoryId;
}
