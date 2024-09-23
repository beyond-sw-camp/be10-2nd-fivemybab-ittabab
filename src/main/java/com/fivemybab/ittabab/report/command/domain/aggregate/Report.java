package com.fivemybab.ittabab.report.command.domain.aggregate;

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
    private Long reportId;  // 신고 ID
    private String reportTitle;  // 신고 제목
    private String reportContent;  // 신고 내용
    @Enumerated(value = EnumType.STRING)
    private Target target;  // 신고 대상
    private Long targetId;  // 대상 ID
    private Long userId;  // 신고 작성자 (회원)
    private LocalDateTime createDate;  // 신고 작성일시
    private LocalDateTime blockStartDate;  // 신고 처리일시
    private Boolean isResolved;  // 신고 처리 여부

    // 유연하게 객체 값을 설정 가능
    @Builder
    public Report(String reportTitle, String reportContent, Target target, Long targetId, Long userId, Boolean isResolved) {
        this.reportTitle = reportTitle;
        this.reportContent = reportContent;
        this.target = target;
        this.targetId = targetId;
        this.userId = userId;
        this.createDate = LocalDateTime.now();
        this.isResolved = isResolved;
    }

    // 신고 처리 메서드
    public void resolve(LocalDateTime blockStartDate) {
        this.isResolved = true;
        this.blockStartDate = blockStartDate;
    }
}
