package com.fivemybab.ittabab.user.query.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class CourseDto {

    private Long courseId;
    private Long bootId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String className;
    private int seasonNum;
}
