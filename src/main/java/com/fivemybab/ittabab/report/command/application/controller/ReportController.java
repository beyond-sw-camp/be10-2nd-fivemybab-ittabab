package com.fivemybab.ittabab.report.command.application.controller;



import com.fivemybab.ittabab.report.command.application.dto.CreateReportResponse;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportRequest;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportResponse;
import com.fivemybab.ittabab.report.query.dto.ReportDto;
import com.fivemybab.ittabab.report.command.application.service.ReportService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/report")
public class ReportController {

    private ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    // 신고 생성
    @PostMapping("/create")
    public ResponseEntity<CreateReportResponse> createReport(@RequestBody CreateReportResponse createReportResponse, @RequestParam Long userId) {
        createReportResponse.setCreateDate(LocalDateTime.now());
        reportService.createReport(createReportResponse, userId);
        return new ResponseEntity<>(createReportResponse,HttpStatus.CREATED);
    }

//    // 모든 신고 조회
//    @GetMapping("/list")
//    public ResponseEntity<List<ReportDto>> getAllReports() {
//        List<ReportDto> reportList =reportService.getAllReports();
//        return new ResponseEntity<>(reportList,HttpStatus.OK);
//    }

    // 신고 처리 (관리자만 가능)
    @PostMapping("/resolve/{reportId}")
    public ResponseEntity<ResolveReportResponse> resolveReport(@PathVariable Long reportId,@RequestBody ResolveReportRequest request) {
        return ResponseEntity.ok(reportService.resolveReport(reportId, request.getUserId()));
    }
}
