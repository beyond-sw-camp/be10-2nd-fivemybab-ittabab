package com.fivemybab.ittabab.report.query.controller;

import com.fivemybab.ittabab.report.query.dto.ReportDto;
import com.fivemybab.ittabab.report.query.service.ReportQueryService;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/report")
public class ReportQueryController {

    private final ReportQueryService reportQueryService;

    public ReportQueryController(ReportQueryService reportQueryService) {
        this.reportQueryService = reportQueryService;
    }

    // 모든 신고 조회
    @GetMapping("/list")
    public ResponseEntity<List<ReportDto>> getAllReports(Authentication authentication) throws NotFoundException {

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        boolean isAdmin = userDetails.getAuthorities().stream()
                .anyMatch(grantedAuthority -> grantedAuthority.getAuthority().equals("ADMIN"));

        if (!isAdmin) {
            throw new AccessDeniedException("관리자만 접근할 수 있습니다.");
        }

        List<ReportDto> reportList =reportQueryService.findReportList();
        return new ResponseEntity<>(reportList, HttpStatus.OK);
    }

}
