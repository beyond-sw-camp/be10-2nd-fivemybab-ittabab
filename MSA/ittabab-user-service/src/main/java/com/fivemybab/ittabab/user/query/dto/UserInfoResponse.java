package com.fivemybab.ittabab.user.query.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserInfoResponse {

    private String userId;
    private String username;
    private List<OrderInfoResponse> orderInfo;
}
