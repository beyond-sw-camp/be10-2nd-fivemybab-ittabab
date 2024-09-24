package com.fivemybab.ittabab.user.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {

    private Long courseId;
    private Long bootId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String className;
    private int seasonNum;

}
