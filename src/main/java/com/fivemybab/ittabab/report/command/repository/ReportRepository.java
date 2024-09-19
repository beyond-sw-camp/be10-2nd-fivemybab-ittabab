package com.fivemybab.ittabab.report.command.repository;

import com.fivemybab.ittabab.report.command.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer> {
}
