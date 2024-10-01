package com.fivemybab.ittabab.inquiry.command.application.service;

import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryAnswerResponse;
import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryQuestionResponse;
import com.fivemybab.ittabab.inquiry.command.domain.aggregate.Inquiry;
import com.fivemybab.ittabab.inquiry.command.domain.repository.InquiryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class InquiryCommandServiceTest {

    @Autowired
    private InquiryCommandService inquiryCommandService;

    @Autowired
    private InquiryRepository inquiryRepository;

    private InquiryQuestionResponse inquiryQuestionResponse;
    private InquiryAnswerResponse inquiryAnswerResponse;

    @BeforeEach
    void setUp() {
        // 문의 등록용 데이터 생성
        inquiryQuestionResponse = new InquiryQuestionResponse();
        inquiryQuestionResponse.setInquiryContent("문의 드립니다");
        inquiryQuestionResponse.setInquiryUserId(1L);

        // 답변 등록용 데이터 생성
        inquiryAnswerResponse = new InquiryAnswerResponse();
        inquiryAnswerResponse.setInquiryReply("문의 답변입니다.");
        inquiryAnswerResponse.setResponseUserId(2L); // 관리자 ID 가정
    }

    @Test
    @DisplayName("문의 등록 테스트")
    void registInquiryQuestionTest() {
        // 문의 등록
        inquiryCommandService.registInquiryQuestion(inquiryQuestionResponse);

        // 문의가 제대로 저장되었는지 확인
        Inquiry savedInquiry = inquiryRepository.findAll().get(2); // 등록한 문의가 저장된 번호
        assertNotNull(savedInquiry);
        assertEquals("문의 드립니다", savedInquiry.getInquiryContent());
        assertEquals(1L, savedInquiry.getInquiryUserId());
    }

    @Test
    @DisplayName("문의 답변 등록 테스트")
    void registInquiryAnswerTest() {
        // 먼저 문의를 등록
        inquiryCommandService.registInquiryQuestion(inquiryQuestionResponse);
        Inquiry savedInquiry = inquiryRepository.findAll().get(0);
        Long inquiryId = savedInquiry.getInquiryId();

        // 문의에 대한 답변 등록
        inquiryCommandService.registInquiryAnswer(inquiryId, inquiryAnswerResponse);

        // 답변이 제대로 저장되었는지 확인
        Optional<Inquiry> inquiryOpt = inquiryRepository.findById(inquiryId);
        assertTrue(inquiryOpt.isPresent());

        Inquiry inquiryWithAnswer = inquiryOpt.get();
        assertNotNull(inquiryWithAnswer.getInquiryReply());
        assertEquals("문의 답변입니다.", inquiryWithAnswer.getInquiryReply());
        assertNotNull(inquiryWithAnswer.getInquiryReplyTime());
        assertEquals(2L, inquiryWithAnswer.getResponseUserId());
    }

    @Test
    @DisplayName("존재하지 않는 문의에 답변 등록 시 예외 테스트")
    void registInquiryAnswerToNonExistentInquiryTest() {
        Long nonExistentInquiryId = 999L; // 존재하지 않는 ID

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            inquiryCommandService.registInquiryAnswer(nonExistentInquiryId, inquiryAnswerResponse);
        });

        assertEquals("없는 문의: " + nonExistentInquiryId, exception.getMessage());
    }
}