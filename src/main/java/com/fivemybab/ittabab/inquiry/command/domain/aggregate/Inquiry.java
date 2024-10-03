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

    public void modifyInquiryReply(String inquiryReply) {
        this.inquiryReply = inquiryReply;
    }

    public void modifyInquiryReplyTime(LocalDateTime inquiryReplyTime) {
        this.inquiryReplyTime = inquiryReplyTime;
    }

    public void modifyResponseUserId(Long responseUserId) {
        this.responseUserId = responseUserId;
    }

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }



    @Builder
    public Inquiry(String inquiryContent, LocalDateTime createDate, String inquiryReply, Long inquiryUserId) {
        this.inquiryContent = inquiryContent;
        this.createDate = createDate;
        this.inquiryReply = inquiryReply;
        this.inquiryUserId = inquiryUserId;
    }


}

