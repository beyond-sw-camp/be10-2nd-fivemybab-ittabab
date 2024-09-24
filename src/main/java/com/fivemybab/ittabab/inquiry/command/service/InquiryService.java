package com.fivemybab.ittabab.inquiry.command.service;

import com.fivemybab.ittabab.inquiry.command.dto.InquiryDTO;
import com.fivemybab.ittabab.inquiry.command.entity.InquiryInfo;
import com.fivemybab.ittabab.inquiry.command.repository.InquiryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;
    private final ModelMapper modelMapper;

    public InquiryService(InquiryRepository inquiryRepository, ModelMapper modelMapper) {
        this.inquiryRepository = inquiryRepository;
        this.modelMapper = modelMapper;
    }

    /* 문의 목록 */
    public List<InquiryDTO> findInquiryList() {
        List<InquiryInfo> inquiryList = inquiryRepository.findAll(Sort.by("inquiryId").descending());
        return inquiryList.stream()
                .map(inquiryInfo -> modelMapper.map(inquiryInfo, InquiryDTO.class))
                .toList();
    }

    /* 사용자 문의 목록 */
    public List<InquiryDTO> findInquiryListByUserId(Long userId) {
        List<InquiryInfo> inquiryList = inquiryRepository.findByInquiryUserId(userId, Sort.by("inquiryId").descending());
        return inquiryList.stream()
                .map(inquiryInfo -> modelMapper.map(inquiryInfo, InquiryDTO.class))
                .toList();
    }

    /* 문의 등록 (사용자) */
    @Transactional
    public void registInquiryQuestion(InquiryDTO inquiryDTO) {
        InquiryInfo inquiryInfo = modelMapper.map(inquiryDTO, InquiryInfo.class);

        inquiryInfo.setResponseUserId(null);  // 명시적으로 null 설정
        inquiryRepository.save(inquiryInfo);
    }

    /* 문의 답변 등록 (관리자) */
    @Transactional
    public void registInquiryAnswer(Long inquiryId, String inquiryReply, Long responseUserId) {
        InquiryInfo inquiryInfo = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("없는 문의: " + inquiryId));

        // 답변 등록
        inquiryInfo.setInquiryReply(inquiryReply);
        inquiryInfo.setInquiryReplyTime(LocalDateTime.now());

        // 응답 유저 ID 설정
        inquiryInfo.setResponseUserId(responseUserId);

        inquiryRepository.save(inquiryInfo);
    }

}
