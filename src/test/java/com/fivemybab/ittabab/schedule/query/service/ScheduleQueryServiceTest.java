package com.fivemybab.ittabab.schedule.query.service;

import com.fivemybab.ittabab.schedule.command.application.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.query.mapper.ScheduleMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class ScheduleQueryServiceTest {

    @Autowired
    private ScheduleQueryService scheduleQueryService;

    @Test
    @DisplayName("일정 목록 조회 테스트")
    void findScheduleListTest() {

        List<ScheduleDto> scheduleList = scheduleQueryService.findScheduleList();

        assertNotNull(scheduleList);
        assertEquals(5, scheduleList.size()); // 저장 되어있는 일정 갯수 입력

    }
}
