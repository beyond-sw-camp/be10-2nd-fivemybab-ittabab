package com.fivemybab.ittabab.inquiry.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class InquiryDTO {

    private int inquiryId;
    private String inquiryContent;
    private LocalDate createDate;
    private String inquiryReply;
    private LocalDate inquiryReplyTime;
    private Integer responseMemberId;
    private int inquiryMemberId;

}
