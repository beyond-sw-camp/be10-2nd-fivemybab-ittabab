package com.fivemybab.ittabab.member.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "notification")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int notificationId;
    private int memberId;
    private String notificationTitle;
    private String notificationContent;
    private boolean notificationStatus;
    private LocalDateTime createDate;
    private int groupId;
    private int postId;
    private int commentId;
    private int inquiryId;
    private int reportId;

}
