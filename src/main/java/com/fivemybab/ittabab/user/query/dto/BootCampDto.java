package com.fivemybab.ittabab.user.query.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BootCampDto {

    private Long bootId;
    private String bootName;
    private String bootLocation;
}


