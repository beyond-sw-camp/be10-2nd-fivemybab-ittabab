package com.fivemybab.ittabab.user.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class CreateNotificationRequest {

    private String content;
    private String target;
    private Long targetId;
    private List<Long> userIdList;
}
