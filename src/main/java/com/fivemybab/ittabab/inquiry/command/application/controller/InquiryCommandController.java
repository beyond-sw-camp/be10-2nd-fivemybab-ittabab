package com.fivemybab.ittabab.inquiry.command.application.controller;

import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryAnswerResponse;
import com.fivemybab.ittabab.inquiry.command.application.dto.InquiryQuestionResponse;
import com.fivemybab.ittabab.inquiry.command.application.service.InquiryCommandService;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/inquiry")
@RequiredArgsConstructor
@Tag(name = "Inquiry", description = "문의 관련 API")
public class InquiryCommandController {

    private final InquiryCommandService inquiryCommandService;

    /* 문의 등록 (사용자) */
    @Operation(summary = "문의 등록(사용자)")
    @PostMapping("/user")
    public ResponseEntity<String> registInquiryQuestion(@RequestBody InquiryQuestionResponse inquiryQuestionResponse, @AuthenticationPrincipal CustomUserDetails loginUser) {
        Long userId = loginUser.getUserId();
        inquiryQuestionResponse.setCreateDate(LocalDateTime.now());
        inquiryCommandService.registInquiryQuestion(inquiryQuestionResponse, userId);
        return new ResponseEntity<>("문의 등록(사용자) 완료", HttpStatus.CREATED);
    }

    /* 문의 답변 (관리자) */
    @Operation(summary = "문의 답변(관리자)")
    @PostMapping("/admin/{inquiryId}")
    public ResponseEntity<String> registInquiryAnswer( @PathVariable Long inquiryId,
                                                       @RequestBody InquiryAnswerResponse inquiryAnswerResponse
    ,@AuthenticationPrincipal CustomUserDetails loginUser) {

        inquiryCommandService.registInquiryAnswer(inquiryId, inquiryAnswerResponse, loginUser.getUserId());

        return new ResponseEntity<>("문의 답변 등록(관리자) 완료", HttpStatus.CREATED);
    }
}
