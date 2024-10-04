package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.infrastructure.client.StoreServiceClient;
import com.fivemybab.ittabab.user.query.dto.OrderInfoResponse;
import com.fivemybab.ittabab.user.query.dto.UserInfoResponse;
import com.fivemybab.ittabab.user.query.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserQueryService {

    private final ModelMapper modelMapper;
    private final UserMapper userMapper;
    private final StoreServiceClient storeServiceClient;

    public UserInfoResponse findById(Long id) {

        UserDto userDto = userMapper.findById(id);

        UserInfoResponse userInfoResponse = modelMapper.map(userDto, UserInfoResponse.class);
        List<OrderInfoResponse> orderInfo =  storeServiceClient.storeUserOrderMenuList(28L, 2L);
        userInfoResponse.setOrderInfo(orderInfo);

        return userInfoResponse;
    }

    public List<UserDto> findAll() {
        return userMapper.findAll();
    }

    public Optional<UserInfo> findUserIdByLoginId(Authentication loginUserLoginId) {
        return userMapper.findByLoginId(loginUserLoginId.getName());
    }
}
