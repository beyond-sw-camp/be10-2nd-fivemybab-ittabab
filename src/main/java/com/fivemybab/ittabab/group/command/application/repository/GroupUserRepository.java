package com.fivemybab.ittabab.group.command.application.repository;

import com.fivemybab.ittabab.group.command.domain.aggregate.GroupUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupUserRepository extends JpaRepository<GroupUser, Long> {
}
