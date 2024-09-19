package com.fivemybab.ittabab.inquiry.command.controller;

import com.fivemybab.ittabab.inquiry.command.dto.InquiryDTO;
import com.fivemybab.ittabab.inquiry.command.service.InquiryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/inquiry")
public class InquiryController {

    private final InquiryService inquiryService;

    public InquiryController(InquiryService inquiryService) {
        this.inquiryService = inquiryService;
    }

    /* 문의 전체 조회 (관리자) */
    @GetMapping("/list")
    public String findInquiryList(Model model){
        List<InquiryDTO> inquiryList = inquiryService.findInquiryList();
        model.addAttribute("inquiryList", inquiryList);
        return "inquiry/list";
    }

    /* 문의 조회 (사용자)*/
    @GetMapping("/list/member/{memberId}")
    public String findInquiryListByMemberId(@PathVariable int memberId, Model model) {
        List<InquiryDTO> inquiryList = inquiryService.findInquiryListByMemberId(memberId);
        model.addAttribute("inquiryList", inquiryList);
        return "inquiry/list";
    }

    /* 문의 등록 (사용자) */
    @GetMapping("/question")
    public String registInquiryQuestionForm() {
        return "inquiry/question";
    }

    @PostMapping("/question")
    public String registInquiryQeustion(@ModelAttribute InquiryDTO inquiryDTO) {
        inquiryService.registInquiryQuestion(inquiryDTO);
        return "redirect:/inquiry/list";
    }

    /* 문의 답변 (관리자) */
    @GetMapping("/answer/{inquiryId}")
    public String registInquiryAnswerForm(@PathVariable int inquiryId, Model model) {
        model.addAttribute("inquiryId", inquiryId);
        return "inquiry/answer";
    }

    @PostMapping("/answer/{inquiryId}")
    public String registInquiryAnswer(@ModelAttribute InquiryDTO inquiryDTO,
                                      @RequestParam Integer responseMemberId) {
        System.out.println("Reply Content: " + inquiryDTO.getInquiryReply());
        inquiryService.registInquiryAnswer(inquiryDTO.getInquiryId(), inquiryDTO.getInquiryReply(), responseMemberId);
        return "redirect:/inquiry/list";
    }
}
