package com.fivemybab.ittabab.schedule.command.application.controller;

import com.fivemybab.ittabab.schedule.command.application.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.command.application.service.ScheduleService;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    private final ScheduleService scheduleService;
    private final UserRepository userRepository;

    public ScheduleController(ScheduleService scheduleService, UserRepository userRepository) {
        this.userRepository = userRepository;
        this.scheduleService = scheduleService;
    }

    /* 일정 입력 */
    @PostMapping
    public ResponseEntity<String> registSchedule(@RequestBody ScheduleDto scheduleDto, Authentication authentication){
        String username = authentication.getName();
        Long userId = getUserIdFromUsername(username);
        scheduleDto.setUserId(userId);
        scheduleDto.setScheduleDate(LocalDate.now());
        scheduleService.registSchedule(scheduleDto);
        return new ResponseEntity<>("등록 완료", HttpStatus.OK);
    }

    /* 일정 수정 */
    @PutMapping
    public ResponseEntity<String> modifySchedule(@RequestBody ScheduleDto scheduleDto){
        scheduleService.modifySchedule(scheduleDto);
        return new ResponseEntity<>("수정 완료"+scheduleDto, HttpStatus.OK);
    }

    /* 일정 삭제 */
    @DeleteMapping("/{scheduleId}")
    public ResponseEntity<String> deleteSchedule(@PathVariable Long scheduleId) {
        scheduleService.deleteSchedule(scheduleId);
        return ResponseEntity.noContent().build();
    }

    // 사용자 인증
    private Long getUserIdFromUsername(String username) {
        UserInfo userInfo = userRepository.findByUsername(username);
        if (userInfo != null) {
            return userInfo.getUserId();
        }
        throw new IllegalArgumentException("사용자를 찾을 수 없습니다.");
    }
}