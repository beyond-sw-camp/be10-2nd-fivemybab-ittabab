package com.fivemybab.ittabab.inquiry.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "inquiry")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class InquiryInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long inquiryId;
    private String inquiryContent;
    private LocalDateTime createDate;
    @Setter
    private String inquiryReply;
    @Setter
    private LocalDateTime inquiryReplyTime;
    @Setter
    private Long responseUserId;
    private Long inquiryUserId;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDateTime.now();
    }

}

