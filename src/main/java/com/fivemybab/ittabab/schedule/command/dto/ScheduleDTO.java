package com.fivemybab.ittabab.schedule.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@ToString
public class ScheduleDTO {
    private int scheduleId;
    private LocalDate scheduleDate;
    private String scheduleContent;
    private int memberId;
}
