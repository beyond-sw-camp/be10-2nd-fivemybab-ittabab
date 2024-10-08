package com.fivemybab.ittabab.group.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class GroupCommentDto {

    private Long groupCommentId;
    private Long groupId;
    private Long parentCommentId;
    private Long userId;
    private String commentContent;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;

}
