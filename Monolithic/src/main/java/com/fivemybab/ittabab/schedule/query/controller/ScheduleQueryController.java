package com.fivemybab.ittabab.schedule.query.controller;

import com.fivemybab.ittabab.schedule.command.application.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.query.service.ScheduleQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/schedule")
@RequiredArgsConstructor
@Tag(name = "Schedule", description = "일정 관련 API")
public class ScheduleQueryController {

    private final ScheduleQueryService scheduleQueryService;

    /* 전체 일정 조회 */
    @Operation(summary = "전체 일정 조회")
    @GetMapping
    public ResponseEntity<List<ScheduleDto>> scheduleList() {
        List<ScheduleDto> scheduleList = scheduleQueryService.findScheduleList();

        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }
}
