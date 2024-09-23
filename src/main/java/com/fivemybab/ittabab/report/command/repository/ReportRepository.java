package com.fivemybab.ittabab.report.command.repository;

import com.fivemybab.ittabab.report.command.domain.aggregate.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
}
