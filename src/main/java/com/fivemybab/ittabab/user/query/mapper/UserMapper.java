package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDto findById(@Param("id") Long id);

    List<UserDto> findAll();
}
