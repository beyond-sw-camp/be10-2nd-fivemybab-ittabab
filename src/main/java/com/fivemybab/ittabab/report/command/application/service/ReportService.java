package com.fivemybab.ittabab.report.command.application.service;

import com.fivemybab.ittabab.report.command.application.dto.ResolveReportResponse;
import com.fivemybab.ittabab.report.command.domain.aggregate.Target;
import com.fivemybab.ittabab.report.command.application.dto.CreateReportResponse;

import com.fivemybab.ittabab.report.command.domain.aggregate.Report;
import com.fivemybab.ittabab.report.command.domain.repository.ReportRepository;
import com.fivemybab.ittabab.report.query.dto.ReportDto;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public ReportService(ReportRepository reportRepository, UserRepository userRepository, ModelMapper modelMapper) {
        this.reportRepository = reportRepository;
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    // 신고 생성
    public CreateReportResponse createReport(CreateReportResponse createReportResponse, Long userId) {
        UserInfo user = userRepository.findByUserId(userId);  // 작성자 조회
        if (user == null) {
            throw new IllegalArgumentException("회원 정보를 찾을 수 없습니다.");
        }

        // 신고 객체 생성
        Report report = Report.builder()
                .reportTitle(createReportResponse.getReportTitle())
                .reportContent(createReportResponse.getReportContent())
                .target(Target.valueOf(createReportResponse.getReportTarget()))
                .targetId(createReportResponse.getTargetId())
                .userId(userId)
                .isResolved(false)
                .build();

        reportRepository.save(report);
        return modelMapper.map(report, CreateReportResponse.class);
    }

    // 모든 신고 조회 (관리자)
//    public List<ReportDto> getAllReports() {
//        List<ReportDto> reports = reportRepository.findAll().stream().map(report -> modelMapper.map(report, ReportDto.class)).toList();
//        return reports;
//    }

    // 신고 처리 (관리자)
    public ResolveReportResponse resolveReport(Long reportId, Long userId) {
        Report report = reportRepository.findById(reportId)
                .orElseThrow(() -> new IllegalArgumentException("신고를 찾을 수 없습니다."));

        UserInfo admin = userRepository.findByUserId(userId);

        if (admin.getUserRole().equals("ADMIN")) {  // 관리자가 아닌 경우 예외 발생
            throw new IllegalArgumentException("관리자 권한이 없습니다.");
        }

        // 신고 처리
        report.resolve(LocalDateTime.now());
        reportRepository.save(report);  // 변경 사항 저장

        return modelMapper.map(report, ResolveReportResponse.class);
    }

}
