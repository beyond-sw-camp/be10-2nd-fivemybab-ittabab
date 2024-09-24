package com.fivemybab.ittabab.user.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class BootCampDTO {

    private Long bootId;
    private String bootName;
    private String bootLocation;
}
