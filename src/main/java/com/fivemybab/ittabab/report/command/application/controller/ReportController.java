package com.fivemybab.ittabab.report.command.application.controller;



import com.fivemybab.ittabab.report.command.application.dto.CreateReportRequest;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportRequest;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportResponse;
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
    @PostMapping
    public ResponseEntity<CreateReportRequest> createReport(@RequestBody CreateReportRequest createReportRequest, @RequestParam Long userId) {
        createReportRequest.setCreateDate(LocalDateTime.now());
        reportService.createReport(createReportRequest, userId);
        return new ResponseEntity<>(createReportRequest,HttpStatus.CREATED);
    }

//    // 모든 신고 조회
//    @GetMapping("/list")
//    public ResponseEntity<List<ReportDto>> getAllReports() {
//        List<ReportDto> reportList =reportService.getAllReports();
//        return new ResponseEntity<>(reportList,HttpStatus.OK);
//    }

    // 신고 처리 (관리자만 가능)
    @PostMapping("/{reportId}")
    public ResponseEntity<ResolveReportResponse> resolveReport(@PathVariable Long reportId,@RequestBody ResolveReportRequest request) {
        return ResponseEntity.ok(reportService.resolveReport(reportId, request.getUserId()));
    }
}
