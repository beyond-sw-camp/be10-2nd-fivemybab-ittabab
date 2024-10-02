package com.fivemybab.ittabab.report.command.domain.repository;

import com.fivemybab.ittabab.report.command.domain.aggregate.Report;
import com.fivemybab.ittabab.report.command.domain.aggregate.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    boolean existsByUserIdAndTargetIdAndTarget(Long userId, Long targetId, Target target);
}
