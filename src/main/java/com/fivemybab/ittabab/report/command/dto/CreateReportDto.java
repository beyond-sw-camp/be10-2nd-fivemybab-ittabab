package com.fivemybab.ittabab.report.command.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateReportDto {

    private String reportTitle;
    private String reportContent;
    private String reportTarget;
    private LocalDateTime createDate;
    private Long targetId;

}
