package com.fivemybab.ittabab.user.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CreateCourseRequest {

    private LocalDate startDate;
    private LocalDate endDate;
    private String className;
    private int seasonNum;
}
