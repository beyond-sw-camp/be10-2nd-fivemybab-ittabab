package com.fivemybab.ittabab.report.command.application.controller;



import com.fivemybab.ittabab.report.command.application.dto.CreateReportRequest;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportRequest;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportResponse;
import com.fivemybab.ittabab.report.command.application.service.ReportService;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
@RequestMapping("/report")
@Tag(name = "Report", description = "신고 관련 API")
public class ReportController {

    private final ReportService reportService;

    // 신고 생성
    @Operation(summary = "신고 생성")
    @PostMapping("/user")
    public ResponseEntity<CreateReportRequest> createReport(@RequestBody CreateReportRequest createReportRequest, @AuthenticationPrincipal CustomUserDetails loginUser) {
        createReportRequest.setCreateDate(LocalDateTime.now());
        reportService.createReport(createReportRequest, loginUser.getUserId());
        return new ResponseEntity<>(createReportRequest,HttpStatus.CREATED);
    }


    // 신고 처리 (관리자만 가능)
    @Operation(summary = "신고 처리 (관리자)")
    @PostMapping("/admin/{reportId}")
    public ResponseEntity<ResolveReportResponse> resolveReport(@PathVariable("reportId") Long reportId,@RequestBody ResolveReportRequest request) {
        return ResponseEntity.ok(reportService.resolveReport(reportId, request));
    }
}
