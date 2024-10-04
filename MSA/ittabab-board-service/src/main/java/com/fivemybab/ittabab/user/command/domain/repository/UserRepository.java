package com.fivemybab.ittabab.user.command.domain.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;

import java.util.Optional;

public interface UserRepository {

    UserInfo findByUsername(String username);

    UserInfo findByUserId(Long userId);

    Optional<UserInfo> findByLoginId(String loginId);

    UserInfo findByEmail(String email);
}
