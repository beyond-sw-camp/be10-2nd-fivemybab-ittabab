package com.fivemybab.ittabab.inquiry.command.controller;

import com.fivemybab.ittabab.inquiry.command.dto.InquiryDTO;
import com.fivemybab.ittabab.inquiry.command.service.InquiryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }
    @GetMapping("/list")
    public ResponseEntity<List<InquiryDTO>> findInquiryList() {
        List<InquiryDTO> inquiryList = inquiryService.findInquiryList();
        return new ResponseEntity<>(inquiryList, HttpStatus.OK);
    }

    /* 문의 조회 (사용자)*/
    @GetMapping("/list/user/{userId}")
    public ResponseEntity<List<InquiryDTO>> findInquiryListByUserId(@PathVariable Long userId) {
        List<InquiryDTO> inquiryId = inquiryService.findInquiryListByUserId(userId);
        return new ResponseEntity<>(inquiryId,HttpStatus.OK);
    }

    /* 문의 등록 (사용자) */
    @PostMapping("/question")
    public ResponseEntity<String> registInquiryQuestion(@RequestBody InquiryDTO inquiryDTO) {
        inquiryDTO.setCreateDate(LocalDateTime.now());
        inquiryService.registInquiryQuestion(inquiryDTO);
        return new ResponseEntity<>("문의 등록(사용자) 완료", HttpStatus.CREATED);
    }


    /* 문의 답변 (관리자) */
    @PostMapping("/answer/{inquiryId}")
    public ResponseEntity<String> registInquiryAnswer( @PathVariable Long inquiryId,
                                                       @RequestBody InquiryDTO inquiryDTO) {
        if (inquiryDTO.getResponseUserId() == null || inquiryDTO.getInquiryReply() == null) {
            return new ResponseEntity<>("responseUserId 또는 inquiryReply가 누락되었습니다.", HttpStatus.BAD_REQUEST);
        }

        // 서비스 호출로 답변을 등록
        inquiryService.registInquiryAnswer(inquiryId, inquiryDTO.getInquiryReply(), inquiryDTO.getResponseUserId());

        return new ResponseEntity<>("문의 답변 등록(관리자) 완료", HttpStatus.CREATED);
    }
}
