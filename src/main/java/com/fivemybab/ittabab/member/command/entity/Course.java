package com.fivemybab.ittabab.member.command.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "course")
@Table(name = "course")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int courseId;
    private LocalDate startDate;
    private LocalDate endDate;
    private String className;
    private int seasonNum;

    @ManyToOne
    @JoinColumn(name = "boot_id")
    private BootCamp bootcamp;

    @OneToMany(mappedBy = "course")
    private List<MemberInfo> members;

    @Builder
    public Course(LocalDate startDate, LocalDate endDate, String className, int seasonNum, BootCamp bootcamp) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.className = className;
        this.seasonNum = seasonNum;
        this.bootcamp = bootcamp;
    }

    public void modifyStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void modifyEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void modifyClassName(String className) {
        this.className = className;
    }

    public void modifySeasonNum(int seasonNum) {
        this.seasonNum = seasonNum;
    }
}
