package com.fivemybab.ittabab.group.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class GroupMemberDTO {
    private int groupMemberId;
    private int memberId;
    private int groupId;
}
