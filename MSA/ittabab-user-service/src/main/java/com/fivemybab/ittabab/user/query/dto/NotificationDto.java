package com.fivemybab.ittabab.user.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class NotificationDto {

    private Long notificationId;
    private String content;
    private String target;
    private Long targetId;
    private Long userId;
    private boolean isRead;
    private LocalDateTime createDate;
}
