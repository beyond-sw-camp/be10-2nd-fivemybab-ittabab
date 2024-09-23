package com.fivemybab.ittabab.report.command.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReportDTO {

    private String reportTitle;
    private String reportContent;
    private String reportTarget;
    private Long targetId;

}
