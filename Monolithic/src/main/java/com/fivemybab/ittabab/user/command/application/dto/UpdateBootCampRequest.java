package com.fivemybab.ittabab.user.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateBootCampRequest {

    private String bootName;
    private String address;
}
