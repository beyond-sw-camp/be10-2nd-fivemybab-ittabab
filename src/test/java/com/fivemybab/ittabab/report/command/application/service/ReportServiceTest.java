package com.fivemybab.ittabab.report.command.application.service;

import static org.junit.jupiter.api.Assertions.*;

import com.fivemybab.ittabab.report.command.application.dto.CreateReportRequest;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportRequest;
import com.fivemybab.ittabab.report.command.application.dto.ResolveReportResponse;
import com.fivemybab.ittabab.report.command.domain.repository.ReportRepository;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class ReportServiceTest {

    @Autowired
    private ReportService reportService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ReportRepository reportRepository;

    private UserInfo testUser;


    @Test
    @DisplayName("신고 생성 - 성공")
    void createReport_Success() {
        Long loginUserId = 4L;
        CreateReportRequest request = new CreateReportRequest();
        request.setReportTitle("테스트 신고");
        request.setReportContent("이것은 테스트 신고입니다.");
        request.setReportTarget("POST");
        request.setTargetId(7L);

        CreateReportRequest createdReport = reportService.createReport(request, loginUserId);

        assertNotNull(createdReport);
        assertEquals(request.getReportTitle(), createdReport.getReportTitle());
        assertEquals(request.getReportContent(), createdReport.getReportContent());
    }

    @Test
    @DisplayName("본인의 게시물 신고 시 예외 발생")
    void createReport_OwnPost_Exception() {
        Long loginUserId = 1L;
        CreateReportRequest request = new CreateReportRequest();
        request.setReportTitle("테스트 신고");
        request.setReportContent("이것은 테스트 신고입니다.");
        request.setReportTarget("POST");
        request.setTargetId(7L);

        assertThrows(IllegalArgumentException.class, () -> {
            reportService.createReport(request, loginUserId);
        }, "본인의 게시물은 신고할 수 없습니다.");
    }

    @Test
    @DisplayName("이미 신고한 대상을 신고 시 예외 발생")
    void createReport_AlreadyReported_Exception() {
        Long loginUserId = 2L;
        CreateReportRequest request = new CreateReportRequest();
        request.setReportTitle("테스트 신고");
        request.setReportContent("이것은 테스트 신고입니다.");
        request.setReportTarget("POST");
        request.setTargetId(7L);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            reportService.createReport(request, loginUserId);
        });

        // 예외 메시지 확인
        assertEquals("이미 신고한 대상입니다.", exception.getMessage());
    }

    @Test
    @DisplayName("신고 처리 - 성공")
    void resolveReport_Success() {
        // 신고 ID
        Long reportId = reportRepository.findAll().get(2).getUserId(); // 신고의 ID

        ResolveReportRequest resolveRequest = new ResolveReportRequest();
        resolveRequest.setIsResolved(true);

        ResolveReportResponse response = reportService.resolveReport(reportId, resolveRequest);

        assertNotNull(response);
        assertTrue(response.isResolved());
    }

    @Test
    @DisplayName("신고 ID로 신고를 찾을 수 없을 때 예외 발생")
    void resolveReport_ReportNotFound_Exception() {
        ResolveReportRequest request = new ResolveReportRequest();

        assertThrows(IllegalArgumentException.class, () -> {
            reportService.resolveReport(999L, request); // 존재하지 않는 신고 ID
        }, "신고를 찾을 수 없습니다.");
    }
}