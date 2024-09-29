package com.fivemybab.ittabab.report.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResolveReportRequest {
    private Long userId;
    private Boolean isResolved;
}

