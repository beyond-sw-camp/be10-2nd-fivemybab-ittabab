package com.fivemybab.ittabab.inquiry.command.application.service;

import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryQuestionResponse;
import com.fivemybab.ittabab.inquiry.command.domain.aggregate.Inquiry;
import com.fivemybab.ittabab.inquiry.command.domain.repository.InquiryRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class InquiryCommandService {

    private final InquiryRepository inquiryRepository;
    private final ModelMapper modelMapper;

    public InquiryCommandService(InquiryRepository inquiryRepository, ModelMapper modelMapper) {
        this.inquiryRepository = inquiryRepository;
        this.modelMapper = modelMapper;
    }

    /* 문의 목록 */
//    public List<InquiryDto> findInquiryList() {
//        List<InquiryInfo> inquiryList = inquiryRepository.findAll(Sort.by("inquiryId").descending());
//        return inquiryList.stream()
//                .map(inquiryInfo -> modelMapper.map(inquiryInfo, InquiryDto.class))
//                .toList();
//    }

    /* 사용자 문의 목록 */
//    public List<InquiryDto> findInquiryListByUserId(Long userId) {
//        List<InquiryInfo> inquiryList = inquiryRepository.findByInquiryUserId(userId, Sort.by("inquiryId").descending());
//        return inquiryList.stream()
//                .map(inquiryInfo -> modelMapper.map(inquiryInfo, InquiryDto.class))
//                .toList();
//    }

    /* 문의 등록 (사용자) */
    @Transactional
    public void registInquiryQuestion(InquiryQuestionResponse inquiryQuestionResponse) {
        Inquiry inquiryInfo = modelMapper.map(inquiryQuestionResponse, Inquiry.class);

        inquiryInfo.setResponseUserId(null);  // 명시적으로 null 설정
        inquiryRepository.save(inquiryInfo);
    }

    /* 문의 답변 등록 (관리자) */
    @Transactional
    public void registInquiryAnswer(Long inquiryId, String inquiryReply, Long responseUserId) {
        Inquiry inquiryInfo = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("없는 문의: " + inquiryId));

        // 답변 등록
        inquiryInfo.setInquiryReply(inquiryReply);
        inquiryInfo.setInquiryReplyTime(LocalDateTime.now());

        // 응답 유저 ID 설정
        inquiryInfo.setResponseUserId(responseUserId);

        inquiryRepository.save(inquiryInfo);
    }

}
