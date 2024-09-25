package com.fivemybab.ittabab.user.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString

public class UserDto {

    private Long userId;
    private String username;
    private String loginId;
    private String pwd;
    private String email;
    private String phone;
    private LocalDate birth;
    private Long courseId;
    private String userStatus;
    private String userRole;
    private LocalDate signUpDate;
    private LocalDate signOutDate;

}