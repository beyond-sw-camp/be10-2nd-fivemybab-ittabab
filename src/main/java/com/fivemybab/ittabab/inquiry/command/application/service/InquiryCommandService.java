package com.fivemybab.ittabab.inquiry.command.application.service;

import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryAnswerResponse;
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

    /* 문의 등록 (사용자) */
    @Transactional
    public void registInquiryQuestion(InquiryQuestionResponse inquiryQuestionResponse) {
        Inquiry inquiryInfo = modelMapper.map(inquiryQuestionResponse, Inquiry.class);

        inquiryRepository.save(inquiryInfo);
    }

    /* 문의 답변 등록 (관리자) */
    @Transactional
    public void registInquiryAnswer(Long inquiryId, InquiryAnswerResponse inquiryAnswerResponse) {
        Inquiry inquiryInfo = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new IllegalArgumentException("없는 문의: " + inquiryId));

        // 답변 등록
        inquiryInfo.setInquiryReply(inquiryAnswerResponse.getInquiryReply());
        inquiryInfo.setInquiryReplyTime(LocalDateTime.now());

        // 응답 유저 ID 설정
        inquiryInfo.setResponseUserId(inquiryAnswerResponse.getResponseUserId());

        inquiryRepository.save(inquiryInfo);
    }

}
