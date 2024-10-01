package com.fivemybab.ittabab.schedule.command.application.service;

import com.fivemybab.ittabab.schedule.command.application.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.command.domain.aggregate.ScheduleInfo;
import com.fivemybab.ittabab.schedule.command.domain.repository.ScheduleRepository;
import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

@Service
public class ScheduleCommandService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public ScheduleCommandService(ScheduleRepository scheduleRepository, ModelMapper modelMapper, UserRepository userRepository) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }


    /* 일정 추가 */
    @Transactional
    public void registSchedule(ScheduleDto scheduleDto) {
        scheduleRepository.save(modelMapper.map(scheduleDto, ScheduleInfo.class));
    }

    /* 일정 수정 */
    @Transactional
    public void modifySchedule(ScheduleDto scheduleDto) {
        ScheduleInfo foundSchedule = scheduleRepository.findById(scheduleDto.getScheduleId())
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다."));

        foundSchedule.modifyScheduleTitle(scheduleDto.getScheduleTitle());
        foundSchedule.modifyScheduleContent(scheduleDto.getScheduleContent());
        foundSchedule.modifyScheduleDate(scheduleDto.getScheduleDate());
    }

    /* 일정 삭제 */
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        if (!scheduleRepository.existsById(scheduleId)) {
            throw new EntityNotFoundException("삭제하려는 일정이 존재하지 않습니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }

    public Long getScheduleById(Long scheduleId) {
        ScheduleInfo schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new EntityNotFoundException("일정을 찾을 수 없습니다."));
        return schedule.getScheduleId();
    }
}
