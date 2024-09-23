package com.fivemybab.ittabab.report.command.service;

import com.fivemybab.ittabab.report.command.domain.aggregate.Target;
import com.fivemybab.ittabab.report.command.dto.CreateReportDTO;
import com.fivemybab.ittabab.report.command.dto.ReportDTO;
import com.fivemybab.ittabab.report.command.domain.aggregate.Report;
import com.fivemybab.ittabab.report.command.repository.ReportRepository;
import com.fivemybab.ittabab.report.command.repository.UserRepository;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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
    public ReportDTO createReport(CreateReportDTO createReportDTO, Long userId) {
        UserInfo user = userRepository.findByUserId(userId);  // 작성자 조회
        if (user == null) {
            throw new IllegalArgumentException("회원 정보를 찾을 수 없습니다.");
        }

        // 신고 객체 생성
        Report report = Report.builder()
                .reportTitle(createReportDTO.getReportTitle())
                .reportContent(createReportDTO.getReportContent())
                .target(Target.valueOf(createReportDTO.getReportTarget()))
                .targetId(createReportDTO.getTargetId())
                .userId(userId)
                .isResolved(false)
                .build();

        reportRepository.save(report);
        return modelMapper.map(report, ReportDTO.class);
    }

    // 모든 신고 조회 (관리자)
    public List<ReportDTO> getAllReports() {
        List<ReportDTO> reports = reportRepository.findAll().stream().map(report -> modelMapper.map(report, ReportDTO.class)).toList();
        return reports;
    }

    // 신고 처리 (관리자)
    public ReportDTO resolveReport(Long reportId, Long userId) {
        Optional<Report> reportOpt = reportRepository.findById(reportId);
        if (reportOpt.isPresent()) {
            Report report = reportOpt.get();
            UserInfo admin = userRepository.findByUserId(userId);

            if (admin.isUserRole()) {  // 관리자 여부 확인, 임의로 false를 관리자로 함
                throw new IllegalArgumentException("본인은 관리자가 아닙니다.");
            }

            // 신고 처리
            report.resolve(LocalDateTime.now());

            reportRepository.save(report);  // 변경 사항 저장

            return modelMapper.map(report, ReportDTO.class);
        } else {
            throw new IllegalArgumentException("신고를 찾을 수 없습니다.");
        }
    }

}
