package com.fivemybab.ittabab.report.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "report_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;
    private int memberId;
    private String reportContent;
    private LocalDate createDate;
    private boolean reportStatus;
    private int postId;
    private int postCommentId;
    private int groupId;
    private int groupCommentId;
    private int reviewId;
}
