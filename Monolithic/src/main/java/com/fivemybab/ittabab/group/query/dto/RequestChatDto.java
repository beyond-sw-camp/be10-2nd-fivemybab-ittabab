package com.fivemybab.ittabab.group.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class RequestChatDto {

    private Long groupId;
    private String message;
}
