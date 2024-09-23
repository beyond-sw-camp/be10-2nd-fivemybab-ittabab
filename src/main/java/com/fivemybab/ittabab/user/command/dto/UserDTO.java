package com.fivemybab.ittabab.user.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

/**
 * UserDTO
 * 회원 정보를 담는 데이터 전송 객체
 */
@Getter
@Setter
@ToString

public class UserDTO {

    private Long userId;
    private String username;
    private String loginId;
    private String pwd;
    private String email;
    private String phone;
    private LocalDate birth;
    private Long courseId;
    private boolean userStatus;
    private boolean userRole;
    private LocalDate signUpDate;
    private LocalDate signOutDate;

}
