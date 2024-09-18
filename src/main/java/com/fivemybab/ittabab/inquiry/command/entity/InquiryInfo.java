package com.fivemybab.ittabab.schedule.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "inquiry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inquiryId;
    private String inquiryContent;
    private LocalDate createDate;
    private String inquiryReply;
    private LocalDate inquiryReplyTime;
    private int responseMemberId;
    private int inquiryMemberId;

}
