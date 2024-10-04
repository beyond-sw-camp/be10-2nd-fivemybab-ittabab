package com.fivemybab.ittabab.report.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateReportRequest {

    private String reportTitle;
    private String reportContent;
    private String reportTarget;
    private LocalDateTime createDate;
    private Long targetId;

}
