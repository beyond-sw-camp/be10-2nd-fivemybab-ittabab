package com.fivemybab.ittabab.schedule.query.mapper;

import com.fivemybab.ittabab.schedule.command.application.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.command.domain.aggregate.ScheduleInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ScheduleMapper {
    List<ScheduleDto> scheduleList();
}