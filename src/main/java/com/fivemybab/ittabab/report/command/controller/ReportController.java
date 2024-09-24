package com.fivemybab.ittabab.report.command.controller;



import com.fivemybab.ittabab.report.command.dto.CreateReportDTO;
import com.fivemybab.ittabab.report.command.dto.ReportDTO;
import com.fivemybab.ittabab.report.command.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 신고 생성
    @PostMapping("/create")
    public ResponseEntity<CreateReportDTO> createReport(@RequestBody CreateReportDTO createReportDto, @RequestParam Long userId) {
        createReportDto.setCreateDate(LocalDateTime.now());
        reportService.createReport(createReportDto, userId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // 모든 신고 조회
    @GetMapping("/list")
    public ResponseEntity<List<ReportDTO>> getAllReports() {
        List<ReportDTO> reportList =reportService.getAllReports();
        return new ResponseEntity<>(reportList,HttpStatus.OK);
    }

    // 신고 처리 (관리자만 가능)
    @PostMapping("/resolve/{reportId}")
    public ResponseEntity<ReportDTO> resolveReport(@PathVariable Long reportId, @RequestParam Long adminId) {
        return ResponseEntity.ok(reportService.resolveReport(reportId, adminId));
    }
}
