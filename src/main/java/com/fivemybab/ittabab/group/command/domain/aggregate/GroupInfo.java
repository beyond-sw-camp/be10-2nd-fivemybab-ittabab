package com.fivemybab.ittabab.group.command.domain.aggregate;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "group_info")
public class GroupInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupId;
    private Long groupCategoryId;
    private Long userId;
    private String groupTitle;
    private int userCounting;
    private boolean groupStatus;
    private LocalDateTime createDate;
    private LocalDateTime endDate;
    private String groupPost;
    private boolean isBlinded;
    @Enumerated(EnumType.STRING)
    private ChatRoomStatus chatRoomStatus;

    public void modifyUserCounting(int userCounting) {
        this.userCounting = userCounting;
    }

    public void modifyEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public void modifyGroupCategoryId(Long groupCategoryId) {
        this.groupCategoryId = groupCategoryId;
    }

    public void modifyGroupPost(String groupPost) {
        this.groupPost = groupPost;
    }

    public void modifyChatRoomStatus(ChatRoomStatus chatRoomStatus) {
        this.chatRoomStatus = chatRoomStatus;
    }
}
