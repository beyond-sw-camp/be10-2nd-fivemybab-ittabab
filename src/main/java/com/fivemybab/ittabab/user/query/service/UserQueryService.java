package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import com.fivemybab.ittabab.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final UserMapper userMapper;

    public UserDto findById(Long id) {
        return userMapper.findById(id);
    }

    public List<UserDto> findAll() {
        return userMapper.findAll();
    }

    // 로그인한 유저의 로그인 아이디 -> 유저 아이디로 변환 메소드
    public Long loginIdToUserId(Authentication loginUserLoginId) {
        return userMapper.loginIdToUserId(loginUserLoginId.getName());
    }
}
