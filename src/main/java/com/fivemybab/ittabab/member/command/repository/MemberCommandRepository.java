package com.fivemybab.ittabab.member.command.repository;

import com.fivemybab.ittabab.member.command.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberCommandRepository extends JpaRepository<MemberInfo, Integer> {
}
