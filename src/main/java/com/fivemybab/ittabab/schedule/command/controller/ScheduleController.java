package com.fivemybab.ittabab.schedule.command.controller;

import com.fivemybab.ittabab.schedule.command.dto.ScheduleDTO;
import com.fivemybab.ittabab.schedule.command.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    public ResponseEntity<List<ScheduleDTO>> scheduleList() {
        List<ScheduleDTO> scheduleList = scheduleService.findScheduleList();

        return new ResponseEntity<>(scheduleList, HttpStatus.OK);
    }

    /* 일정 입력 */
    @PostMapping("/regist")
    public ResponseEntity<String> registSchedule(@RequestBody ScheduleDTO scheduleDTO){
        scheduleDTO.setScheduleDate(LocalDate.now());
        scheduleService.registSchedule(scheduleDTO);
        return new ResponseEntity<>("등록 완료", HttpStatus.OK);
    }

    /* 일정 수정 */
    @PutMapping("/modify")
    public ResponseEntity<String> modifySchedule(@RequestBody ScheduleDTO scheduleDTO){
        scheduleService.modifySchedule(scheduleDTO);
        return new ResponseEntity<>("수정 완료"+scheduleDTO.toString(), HttpStatus.OK);
    }

    /* 일정 삭제 */
    @DeleteMapping("/delete/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

}
