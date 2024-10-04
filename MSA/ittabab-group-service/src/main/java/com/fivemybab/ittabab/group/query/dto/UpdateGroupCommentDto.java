package com.fivemybab.ittabab.group.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class UpdateGroupCommentDto {

    private String commentContent;
    private LocalDateTime updateDate;
}
