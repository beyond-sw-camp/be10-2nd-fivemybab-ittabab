package com.fivemybab.ittabab.schedule.command.domain.repository;

import com.fivemybab.ittabab.schedule.command.domain.aggregate.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleInfo, Long> {
    Long findByScheduleId(Long scheduleId);
}
