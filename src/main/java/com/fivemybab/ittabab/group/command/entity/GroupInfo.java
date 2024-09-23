package com.fivemybab.ittabab.group.command.entity;

import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "group_info")
public class GroupInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupId;
    private int groupCounting;
    private int groupStatus;
    private Date createDate;
    private Date endDate;
    private String groupPost;
    private int memberId;
    private int groupCategoryId;
    private boolean isBlinded;

    public void modifyGroupCounting(int groupCounting) {
        this.groupCounting = groupCounting;
    }

    public void modifyEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void modifyGroupCategoryId(int groupCategoryId) {
        this.groupCategoryId = groupCategoryId;
    }

    public void modifyGroupPost(String groupPost) {
        this.groupPost = groupPost;
    }
}
