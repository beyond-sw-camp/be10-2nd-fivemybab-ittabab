package com.fivemybab.ittabab.member.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import java.util.Date;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {
    private int courseId;
    private int bootId;
    private Date startDate;
    private Date endDate;
    private String className;
    private String courseName;
    private String seasonNum;
}
