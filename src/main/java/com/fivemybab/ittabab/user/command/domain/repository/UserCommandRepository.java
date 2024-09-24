package com.fivemybab.ittabab.user.command.domain.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

// CUD 작업 진행
public interface UserCommandRepository extends JpaRepository<UserInfo,Long> {
}
