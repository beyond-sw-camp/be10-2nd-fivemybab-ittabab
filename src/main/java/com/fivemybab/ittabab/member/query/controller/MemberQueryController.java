package com.fivemybab.ittabab.member.query.controller;

import com.fivemybab.ittabab.member.command.dto.MemberDTO;
import com.fivemybab.ittabab.member.query.service.MemberQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * MemberQueryController
 * 이 클래스는 회원 정보 조회
 * MyBatis
 */
@Controller
@RequestMapping("/api/member")
public class MemberQueryController {

    private final MemberQueryService memberQueryService;

    @Autowired
    public MemberQueryController(MemberQueryService memberQueryService) {
        this.memberQueryService = memberQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> getMemberById(@PathVariable Long id) {
        MemberDTO memberDTO = memberQueryService.findById(id);
        return new ResponseEntity<>(memberDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MemberDTO>> getAllMembers() {
        List<MemberDTO> members = memberQueryService.findAll();
        return new ResponseEntity<>(members, HttpStatus.OK);
    }
}
