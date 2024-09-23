package com.fivemybab.ittabab.report.command.controller;



import com.fivemybab.ittabab.report.command.dto.CreateReportDTO;
import com.fivemybab.ittabab.report.command.dto.ReportDTO;
import com.fivemybab.ittabab.report.command.service.ReportService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 신고 생성
    @PostMapping
    public ReportDTO createReport(@RequestBody CreateReportDTO createReportDto, @RequestParam Long userId) {
        return reportService.createReport(createReportDto, userId);
    }

    // 모든 신고 조회
    @GetMapping
    public List<ReportDTO> getAllReports() {
        return reportService.getAllReports();
    }

    // 신고 처리 (관리자만 가능)
    @PutMapping("/{reportId}/resolve")
    public ReportDTO resolveReport(@PathVariable Long reportId, @RequestParam Long adminId) {
        return reportService.resolveReport(reportId, adminId);
    }
}
