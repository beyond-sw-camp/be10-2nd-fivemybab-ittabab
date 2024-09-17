package com.fivemybab.ittabab.schedule.command.repository;

import com.fivemybab.ittabab.schedule.command.entity.ScheduleInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<ScheduleInfo, Integer> {
}
