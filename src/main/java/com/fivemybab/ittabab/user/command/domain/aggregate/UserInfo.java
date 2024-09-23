package com.fivemybab.ittabab.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "user_info")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UserInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
