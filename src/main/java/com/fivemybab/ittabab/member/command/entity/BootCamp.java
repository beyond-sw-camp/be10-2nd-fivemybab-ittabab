package com.fivemybab.ittabab.member.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Entity(name = "BootCamp")
@Table(name = "BootCamp")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class BootCamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bootId;

    private String bootName;
    private String bootLocation;

    @Builder
    public BootCamp(int bootId, String bootName, String bootLocation) {
        this.bootId = bootId;
        this.bootName = bootName;
        this.bootLocation = bootLocation;
    }

    public void modifyBootId (int bootId){
        this.bootId = bootId;
    }

    public void modifyBootName(String bootName){
        this.bootName = bootName;
    }

    public void modifyBootLocation(String bootLocation){
        this.bootLocation = bootLocation;
    }


}