package com.fivemybab.ittabab.inquiry.command.domain.repository;

import com.fivemybab.ittabab.inquiry.command.domain.aggregate.Inquiry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InquiryRepository extends JpaRepository<Inquiry, Long> {

}
