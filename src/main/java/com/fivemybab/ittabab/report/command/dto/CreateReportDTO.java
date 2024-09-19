package com.fivemybab.ittabab.report.command.dto;

import com.fivemybab.ittabab.report.command.entity.ReportObject;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateReportDTO {
    private String reportTitle;
    private String reportContent;
    private String reportObject;
    private int objectId;
}
