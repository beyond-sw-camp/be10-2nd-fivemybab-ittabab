package com.fivemybab.ittabab.schedule.command.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Date;

@Entity(name = "schedule_info")
@Table(name = "schedule_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int scheduleId;
    private LocalDate scheduleDate;
    private String scheduleContent;
    private int memberId;

    public void modifyScheduleContent(String scheduleContent) {
        this.scheduleContent = scheduleContent;
    }

    public void modifyScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
}
