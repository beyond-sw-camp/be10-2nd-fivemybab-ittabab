package com.fivemybab.ittabab.report.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReportDTO {
    private Long reportId;  // 신고 ID
    private String reportTitle;  // 신고 제목
    private String reportContent;  // 신고 내용
    private String target;  // 신고 대상
    private Long targetId;  // 대상 ID
    private Long userId;  // 신고 작성자 (회원)
    private LocalDateTime createDate;  // 신고 작성일시
    private LocalDateTime blockStartDate;  // 신고 처리일시
    private Boolean isResolved;  // 신고 처리 여부

}
