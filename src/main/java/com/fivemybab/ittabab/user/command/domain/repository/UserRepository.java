package com.fivemybab.ittabab.user.command.domain.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserInfo,Long> {

    UserInfo findByUsername(String username);

    UserInfo findByUserId(Long userId);

    Optional<UserInfo> findByLoginId(String loginId);

}
