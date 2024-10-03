package com.fivemybab.ittabab.schedule.command.domain.repository;

import com.fivemybab.ittabab.schedule.command.domain.aggregate.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScheduleRepository extends JpaRepository<ScheduleInfo, Long> {
    ScheduleInfo findByScheduleId(Long scheduleId);

}
