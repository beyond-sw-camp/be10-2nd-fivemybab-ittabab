package com.fivemybab.ittabab.report.command.service;


import com.fivemybab.ittabab.member.command.entity.MemberInfo;
import com.fivemybab.ittabab.report.command.dto.CreateReportDTO;
import com.fivemybab.ittabab.report.command.dto.ReportDTO;
import com.fivemybab.ittabab.report.command.entity.Report;
import com.fivemybab.ittabab.report.command.entity.ReportObject;
import com.fivemybab.ittabab.report.command.repository.MemberRepository;
import com.fivemybab.ittabab.report.command.repository.ReportRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReportService {

    private final ReportRepository reportRepository;
    private final MemberRepository memberRepository;

    public ReportService(ReportRepository reportRepository, MemberRepository memberRepository) {
        this.reportRepository = reportRepository;
        this.memberRepository = memberRepository;
    }

    // 신고 생성
    public ReportDTO createReport(CreateReportDTO createReportDTO, int memberId) {
        MemberInfo member = memberRepository.findByMemberId(memberId);  // 작성자 조회
        if (member == null) {
            throw new IllegalArgumentException("회원 정보를 찾을 수 없습니다.");
        }

        // 신고 객체 생성
        Report report = Report.builder()
                .reportTitle(createReportDTO.getReportTitle())
                .reportContent(createReportDTO.getReportContent())
                .reportObject(ReportObject.valueOf(createReportDTO.getReportObject()))
                .objectId(createReportDTO.getObjectId())
                .memberId(memberId)
                .isResolved(false)
                .build();

        reportRepository.save(report);
        return mapToDto(report);
    }

    // 모든 신고 조회 (관리자)
    public List<ReportDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    // 신고 처리 (관리자)
    public ReportDTO resolveReport(int reportId, int memberId) {
        Optional<Report> reportOpt = reportRepository.findById(reportId);
        if (reportOpt.isPresent()) {
            Report report = reportOpt.get();
            MemberInfo admin = memberRepository.findByMemberId(memberId);

            if (admin.isMemberStatus()) {  // 관리자 여부 확인, 임의로 false를 관리자로 함
                throw new IllegalArgumentException("본인은 관리자가 아닙니다.");
            }

            // 신고 처리
            report.resolve(LocalDateTime.now());

            reportRepository.save(report);  // 변경 사항 저장

            return mapToDto(report);
        } else {
            throw new IllegalArgumentException("신고를 찾을 수 없습니다.");
        }
    }

    // Report 엔티티를 DTO로 변환
    private ReportDTO mapToDto(Report report) {
        ReportDTO dto = new ReportDTO();
        dto.setReportId(report.getReportId());
        dto.setReportTitle(report.getReportTitle());
        dto.setReportContent(report.getReportContent());
        dto.setReportObject(report.getReportObject().name());
        dto.setObjectId(report.getObjectId());
        dto.setMemberId(report.getMemberId());
        dto.setCreateDate(report.getCreateDate());
        dto.setIsResolved(report.getIsResolved());
        return dto;
    }
}
