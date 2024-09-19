package com.fivemybab.ittabab.report.command.entity;

import com.fivemybab.ittabab.member.command.entity.MemberInfo;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자
@Table(name = "report")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reportId;  // 신고 ID

    private String reportTitle;  // 신고 제목

    private String reportContent;  // 신고 내용

    private ReportObject reportObject;  // 신고 대상

    private int objectId;  // 대상 ID

    private int memberId;  // 신고 작성자 (회원)

    private LocalDateTime createDate;  // 신고 작성일시

    private LocalDateTime blockStartDate;  // 신고 처리일시

    private Boolean isResolved;  // 신고 처리 여부


    // 유연하게 객체 값을 설정 가능
    @Builder
    public Report(String reportTitle, String reportContent, ReportObject reportObject, int objectId, int memberId, Boolean isResolved) {
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
        this.reportObject = reportObject;
        this.objectId = objectId;
        this.memberId = memberId;
        this.createDate = LocalDateTime.now();
        this.isResolved = isResolved;
    }

    // 신고 처리 메서드
    public void resolve(LocalDateTime blockStartDate) {
        this.isResolved = true;
        this.blockStartDate = blockStartDate;
    }
}
