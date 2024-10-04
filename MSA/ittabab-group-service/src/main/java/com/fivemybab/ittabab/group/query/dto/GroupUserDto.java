package com.fivemybab.ittabab.group.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class GroupUserDto {

    private Long groupUserId;
    private Long userId;
    private Long groupId;

}
