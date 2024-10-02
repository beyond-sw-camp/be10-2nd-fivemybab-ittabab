package com.fivemybab.ittabab.inquiry.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;
    private String inquiryContent;
    private LocalDateTime createDate;
    private String inquiryReply;
    private LocalDateTime inquiryReplyTime;
    private Long responseUserId;
    private Long inquiryUserId;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

    public void modifyInquiryReply(String scheduleContent) {
        this.inquiryReply = inquiryReply;
    }

    public void modifyInquiryReplyTime(LocalDateTime scheduleDate) {
        this.inquiryReplyTime = inquiryReplyTime;
    }

    public void modifyResponseUserId(Long responseUserId) {
        this.responseUserId = responseUserId;
    }

    @Builder
    public Inquiry(String inquiryContent, LocalDateTime createDate, String inquiryReply, Long responseUserId, Long inquiryUserId) {
        this.inquiryContent = inquiryContent;
        this.createDate = createDate;
        this.inquiryReply = inquiryReply;
        this.responseUserId = responseUserId;
        this.inquiryUserId = inquiryUserId;
    }


}

