package com.fivemybab.ittabab.schedule.command.controller;

import com.fivemybab.ittabab.schedule.command.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.command.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /* 전체 일정 조회 */
    @GetMapping("/list")
    public ResponseEntity<List<ScheduleDto>> scheduleList() {
        List<ScheduleDto> scheduleList = scheduleService.findScheduleList();

        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }

    /* 일정 입력 */
    @PostMapping("/regist")
    public ResponseEntity<String> registSchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleDto.setScheduleDate(LocalDate.now());
        scheduleService.registSchedule(scheduleDto);
        return new ResponseEntity<>("등록 완료", HttpStatus.OK);
    }

    /* 일정 수정 */
    @PutMapping("/modify")
    public ResponseEntity<String> modifySchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.modifySchedule(scheduleDto);
        return new ResponseEntity<>("수정 완료"+scheduleDto.toString(), HttpStatus.OK);
    }

    /* 일정 삭제 */
    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

}
