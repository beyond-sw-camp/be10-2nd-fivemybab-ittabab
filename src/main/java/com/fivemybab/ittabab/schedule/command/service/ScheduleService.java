package com.fivemybab.ittabab.schedule.command.service;

import com.fivemybab.ittabab.schedule.command.dto.ScheduleDTO;
import com.fivemybab.ittabab.schedule.command.entity.ScheduleInfo;
import com.fivemybab.ittabab.schedule.command.repository.ScheduleRepository;
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

    /* 전체 조회 */
    public List<ScheduleDTO> findScheduleList() {
        List<ScheduleInfo> scheduleList = scheduleRepository.findAll(Sort.by("scheduleId").descending());
        return scheduleList.stream()
                .map(scheduleInfo -> modelMapper.map(scheduleInfo, ScheduleDTO.class))
                .toList();
    }

    /* 일정 추가 */
    @Transactional
    public void registSchedule(ScheduleDTO scheduleDTO) {
        scheduleRepository.save(modelMapper.map(scheduleDTO, ScheduleInfo.class));
    }

    /* 일정 수정 */
    @Transactional
    public void modifySchedule(ScheduleDTO scheduleDTO) {
        ScheduleInfo foundSchedule = scheduleRepository.findById(scheduleDTO.getScheduleId()).orElseThrow(IllegalArgumentException::new);
        foundSchedule.modifyScheduleContent(scheduleDTO.getScheduleContent());
        foundSchedule.modifyScheduleDate(scheduleDTO.getScheduleDate());
    }

    /* 일정 삭제 */
    @Transactional
    public void deleteSchedule(Integer scheduleId) {
        scheduleRepository.deleteById(scheduleId);
    }
}
