package com.fivemybab.ittabab.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "course")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long courseId;
    private Long bootId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String className;
    private int seasonNum;

    public void modifyStart(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void modifyEnd(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void modifyName(String className) {
        this.className = className;
    }

    public void modifyNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }
}
