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

        Inquiry inquiry = inquiryRepository.findById(inquiryId)
                .orElseThrow(() -> new NotFoundException("없는 문의입니다."));

        // 답변 내용과 응답자 정보를 업데이트
        inquiry.modifyInquiryReply(inquiryAnswerResponse.getInquiryReply());
        inquiry.modifyInquiryReplyTime(LocalDateTime.now());
        inquiry.modifyResponseUserId(userId);


        inquiryRepository.save(inquiry);
    }

}
