package com.fivemybab.ittabab.user.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FriendDTO {

    private Long friendId;
    private Long fromUserId;
    private Long toUserId;
    private boolean accept;

}

