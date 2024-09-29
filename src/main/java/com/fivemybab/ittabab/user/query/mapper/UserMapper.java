package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDto findById(Long id);

    List<UserDto> findAll();

    /* 로그인 아이디를 통한 회원 아이디 조회 */
    Long loginIdToUserId(String loginUserLoginId);
}
