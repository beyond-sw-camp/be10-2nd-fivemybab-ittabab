package com.fivemybab.ittabab.report.command.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserInfo, Long> {
    UserInfo findByUserId(Long userId);
}
