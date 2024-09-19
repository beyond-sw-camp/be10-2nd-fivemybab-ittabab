package com.fivemybab.ittabab.member.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class FriendDTO {
    private String friendId;
    private String friendmemberId;
    private String toMemberId;
    private String fromMemberId;
    private boolean accept;
}

