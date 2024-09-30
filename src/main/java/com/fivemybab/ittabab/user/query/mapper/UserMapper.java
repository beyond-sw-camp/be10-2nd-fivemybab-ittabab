package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    UserDto findById(Long id);

    List<UserDto> findAll();

    /* 로그인 아이디를 통한 회원 아이디 조회 */
    Long loginIdToUserId(String loginUserLoginId);

    Optional<UserInfo> findByLoginId(String loginId);
}
