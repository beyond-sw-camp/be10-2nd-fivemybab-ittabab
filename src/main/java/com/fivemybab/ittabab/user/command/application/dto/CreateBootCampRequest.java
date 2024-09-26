package com.fivemybab.ittabab.user.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateBootCampRequest {

    private String bootName;
    private String bootLocation;
}