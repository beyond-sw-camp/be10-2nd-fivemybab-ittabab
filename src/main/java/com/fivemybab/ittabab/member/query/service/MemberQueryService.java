package com.fivemybab.ittabab.member.query.service;

import com.fivemybab.ittabab.member.query.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberQueryService {

    private final MemberMapper memberMapper;

    @Autowired
    public MemberQueryService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }


}
