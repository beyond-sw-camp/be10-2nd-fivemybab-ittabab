package com.fivemybab.ittabab.member.command.controller;

import com.fivemybab.ittabab.member.command.dto.MemberDTO;
import com.fivemybab.ittabab.member.command.service.MemberCommandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * MemberCommandController
 * 이 클래스는 회원 관련 CUD 작업을
 * jpa 이용
 */
@Controller
@RequestMapping("/api/member")
public class MemberCommandController {

    private final MemberCommandService memberService;

    @Autowired
    public MemberCommandController(MemberCommandService memberService) {
        this.memberService = memberService;
    }

    @PostMapping("/create")
    public ResponseEntity<MemberDTO> createMember(@RequestBody MemberDTO memberDTO) {
        MemberDTO createdMember = memberService.createMember(memberDTO);
        return new ResponseEntity<>(createdMember, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<MemberDTO> updateMember(@PathVariable Long id, @RequestBody MemberDTO memberDTO) {
        MemberDTO updatedMember = memberService.updateMember(id, memberDTO);
        return new ResponseEntity<>(updatedMember, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        memberService.deleteMember(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
