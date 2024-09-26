package com.fivemybab.ittabab.board.command.application.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class UpdatedBoardDTO {
    private String postTitle;
    private String postContent;
    private Boolean isBlinded;
    private LocalDateTime createDate;
}