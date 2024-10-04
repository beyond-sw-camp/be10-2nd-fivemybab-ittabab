package com.fivemybab.ittabab.inquiry.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class InquiryQuestionResponse {
    private String inquiryContent;
    private LocalDateTime createDate;

}
