package com.fivemybab.ittabab.report.query.service;

import com.fivemybab.ittabab.report.query.dto.ReportDto;
import com.fivemybab.ittabab.report.query.mapper.ReportMapper;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.javassist.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReportQueryService {

    private final ReportMapper reportMapper;

    @Transactional(readOnly = true)
    public List<ReportDto> findReportList() throws NotFoundException {

        List<ReportDto> reportList = reportMapper.adminReportList();
//        System.out.println("Report List: " + reportList);
        if (reportList.isEmpty()) {
            throw new NotFoundException("들어온 문의 없음");
        }

        return reportList;

    }

}
