package com.fivemybab.ittabab.member.command.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BootCampDTO {

    private int bootId;
    private String bootName;
    private String bootLocation;
}
