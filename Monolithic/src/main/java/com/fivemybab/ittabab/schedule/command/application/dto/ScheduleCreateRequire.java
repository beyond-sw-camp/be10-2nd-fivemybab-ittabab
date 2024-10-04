package com.fivemybab.ittabab.schedule.command.application.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class ScheduleCreateRequire {
    private String scheduleTitle;
    private String scheduleContent;
    private LocalDate scheduleDate;
}
