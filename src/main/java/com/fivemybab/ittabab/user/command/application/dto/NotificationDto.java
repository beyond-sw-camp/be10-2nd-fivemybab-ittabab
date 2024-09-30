package com.fivemybab.ittabab.user.command.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class NotificationDto {

    private String content;
    private String target;
    private Long targetId;
    private Long userId;
}
