package com.fivemybab.ittabab.report.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ResolveReportResponse {

    private Long userId;
    private Long reportId;
    private LocalDateTime blockStartDate;
    private boolean isResolved;

}
