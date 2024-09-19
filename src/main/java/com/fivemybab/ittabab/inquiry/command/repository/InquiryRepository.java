package com.fivemybab.ittabab.inquiry.command.repository;

import com.fivemybab.ittabab.inquiry.command.entity.InquiryInfo;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InquiryRepository extends JpaRepository<InquiryInfo, Integer> {
    List<InquiryInfo> findByInquiryMemberId(int memberId, Sort sort);
}
