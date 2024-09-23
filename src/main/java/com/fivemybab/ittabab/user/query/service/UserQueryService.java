package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.command.dto.UserDTO;
import com.fivemybab.ittabab.user.query.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserQueryService {

    private final UserMapper userMapper;

    @Autowired
    public UserQueryService(UserMapper userMapper) {

        this.userMapper = userMapper;
    }

    public UserDTO findById(Long id) {
        return userMapper.findById(id);
    }

    public List<UserDTO> findAll() {
        return userMapper.findAll();
    }


}
