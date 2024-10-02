package com.fivemybab.ittabab.inquiry.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InquiryAnswerResponse {
    private Long inquiryId;
    private String inquiryReply;
    private LocalDateTime inquiryReplyTime;

}
