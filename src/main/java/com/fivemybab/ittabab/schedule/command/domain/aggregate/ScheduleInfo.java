package com.fivemybab.ittabab.schedule.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "schedule_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ScheduleInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long scheduleId;
    private LocalDate scheduleDate;
    private String scheduleTitle;
    private String scheduleContent;
    private Long userId;

    public void modifyScheduleContent(String scheduleContent) {
        this.scheduleContent = scheduleContent;
    }

    public void modifyScheduleDate(LocalDate scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public void modifyScheduleTitle(String scheduleTitle) {
        this.scheduleTitle = scheduleTitle;
    }
}
