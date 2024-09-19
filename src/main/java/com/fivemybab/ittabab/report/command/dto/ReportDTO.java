package com.fivemybab.ittabab.report.command.dto;

import com.fivemybab.ittabab.report.command.entity.ReportObject;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReportDTO {
    private int reportId;
    private String reportTitle;
    private String reportContent;
    private String reportObject;
    private int objectId;
    private int memberId;
    private LocalDateTime createDate;
    private LocalDateTime blockStartDate;
    private Boolean isResolved;
}
