package com.fivemybab.ittabab.inquiry.command.application.service;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryAnswerResponse;
import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryQuestionResponse;
import com.fivemybab.ittabab.inquiry.command.domain.aggregate.Inquiry;
import com.fivemybab.ittabab.inquiry.command.domain.repository.InquiryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class InquiryCommandService {

    private final InquiryRepository inquiryRepository;
    private final ModelMapper modelMapper;

    /* 문의 등록 (사용자) */
    @Transactional
    public void registInquiryQuestion(InquiryQuestionResponse inquiryQuestionResponse, Long userId) {
        Inquiry inquiryInfo = Inquiry.builder()
                .inquiryContent(inquiryQuestionResponse.getInquiryContent())
                .createDate(LocalDateTime.now())
                .inquiryUserId(userId)
                .build();

        inquiryRepository.save(inquiryInfo);
    }

    /* 문의 답변 등록 (관리자) */
    @Transactional
    public void registInquiryAnswer(Long inquiryId, InquiryAnswerResponse inquiryAnswerResponse, Long userId) {
        Inquiry inquiryInfo = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new NotFoundException("없는 문의: " + inquiryId));

        // 답변 등록
        inquiryInfo.modifyInquiryReply(inquiryAnswerResponse.getInquiryReply());
        inquiryInfo.modifyInquiryReplyTime(LocalDateTime.now());

        // 응답 유저 ID 설정
        inquiryInfo.modifyResponseUserId(userId);

        inquiryRepository.save(inquiryInfo);
    }

}
