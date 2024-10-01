package com.fivemybab.ittabab.report.query.service;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.report.command.application.service.ReportService;
import com.fivemybab.ittabab.report.query.dto.ReportDto;
import com.fivemybab.ittabab.report.query.mapper.ReportMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class ReportQueryServiceTest {

    @Autowired
    private ReportQueryService reportService;

    @Autowired
    private ReportMapper reportMapper;



    @Test
    @DisplayName("신고 내역")
    void findReportList_ReturnsReports()  {

        List<ReportDto> reportList = null;
        reportList = reportService.findReportList();

        assertEquals(1, reportList.size());
        assertEquals("테스트 신고", reportList.get(0).getReportTitle());
//        assertEquals("테스트 신고", reportList.get(1).getReportTitle());
    }

    @Test
    @DisplayName("신고 내역 없음")
    void findReportList_ThrowsNotFoundException() {

        // 예외 발생 확인
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            reportService.findReportList();
        });

        assertEquals("들어온 신고 없음", exception.getMessage());
    }
}