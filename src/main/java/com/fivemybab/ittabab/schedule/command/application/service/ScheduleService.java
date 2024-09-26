package com.fivemybab.ittabab.schedule.command.application.service;

import com.fivemybab.ittabab.schedule.command.application.dto.ScheduleDto;
import com.fivemybab.ittabab.schedule.command.domain.aggregate.ScheduleInfo;
import com.fivemybab.ittabab.schedule.command.domain.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ModelMapper modelMapper;

    public ScheduleService(ScheduleRepository scheduleRepository, ModelMapper modelMapper) {
        this.scheduleRepository = scheduleRepository;
        this.modelMapper = modelMapper;
    }


    /* 일정 추가 */
    @Transactional
    public void registSchedule(ScheduleDto scheduleDto) {
        scheduleRepository.save(modelMapper.map(scheduleDto, ScheduleInfo.class));
    }

    /* 일정 수정 */
    @Transactional
    public void modifySchedule(ScheduleDto scheduleDto) {
        ScheduleInfo foundSchedule = scheduleRepository.findById(scheduleDto.getScheduleId()).orElseThrow(IllegalArgumentException::new);
        foundSchedule.modifyScheduleTitle(scheduleDto.getScheduleTitle());
        foundSchedule.modifyScheduleContent(scheduleDto.getScheduleContent());
        foundSchedule.modifyScheduleDate(scheduleDto.getScheduleDate());
    }

    /* 일정 삭제 */
    @Transactional
    public void deleteSchedule(Long scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
