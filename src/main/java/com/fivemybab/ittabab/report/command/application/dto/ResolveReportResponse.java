package com.fivemybab.ittabab.report.command.application.dto;

import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
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
