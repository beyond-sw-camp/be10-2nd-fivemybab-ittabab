package com.fivemybab.ittabab.member.query.service;

import com.fivemybab.ittabab.member.command.dto.MemberDTO;
import com.fivemybab.ittabab.member.query.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberQueryService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberQueryService(MemberMapper memberMapper) {

        this.memberMapper = memberMapper;
    }

    public MemberDTO findById(Long id) {
        return memberMapper.findById(id);
    }

    public List<MemberDTO> findAll() {
        return memberMapper.findAll();
    }


}
