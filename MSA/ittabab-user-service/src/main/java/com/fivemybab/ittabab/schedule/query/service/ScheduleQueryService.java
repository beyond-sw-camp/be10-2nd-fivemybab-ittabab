package com.fivemybab.ittabab.schedule.query.service;

import com.fivemybab.ittabab.schedule.command.application.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.query.mapper.ScheduleMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleQueryService {

    private final ScheduleMapper scheduleMapper;

    @Transactional(readOnly = true)
    public List<ScheduleDto> findScheduleList() {
        List<ScheduleDto> scheduleList = scheduleMapper.scheduleList();

        return scheduleList;
    }
}
