package com.fivemybab.ittabab.inquiry.command.entity;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

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
    @Setter
    private String inquiryReply;
    @Setter
    private LocalDate inquiryReplyTime;
    @Setter
    private Integer responseMemberId;
    private int inquiryMemberId;

    @PrePersist
    protected void onCreate() {
        this.createDate = LocalDate.now();
    }

}

