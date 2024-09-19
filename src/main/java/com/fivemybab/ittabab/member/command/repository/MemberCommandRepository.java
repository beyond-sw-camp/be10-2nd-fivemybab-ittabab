package com.fivemybab.ittabab.member.command.repository;

import com.fivemybab.ittabab.member.command.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;
// CUD 작업 진행
public interface MemberCommandRepository extends JpaRepository<MemberInfo,Long> {
}
