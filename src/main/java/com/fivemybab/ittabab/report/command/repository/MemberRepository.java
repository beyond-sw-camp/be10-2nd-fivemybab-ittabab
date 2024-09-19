package com.fivemybab.ittabab.report.command.repository;

import com.fivemybab.ittabab.member.command.entity.MemberInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberInfo, Integer> {
    MemberInfo findByMemberId(int memberId);
}
