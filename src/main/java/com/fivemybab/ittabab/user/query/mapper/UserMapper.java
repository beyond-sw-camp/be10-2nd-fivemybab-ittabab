package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.command.dto.UserDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    UserDTO findById(@Param("id") Long id);

    List<UserDTO> findAll();
}
