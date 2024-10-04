package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface UserMapper {

    UserDto findById(Long id);

    List<UserDto> findAll();

    Optional<UserInfo> findByLoginId(String loginId);
}
