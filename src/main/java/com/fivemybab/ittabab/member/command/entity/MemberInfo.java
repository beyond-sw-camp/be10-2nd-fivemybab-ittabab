package com.fivemybab.ittabab.member.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "member_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId;

    private String memberName;
    private String memberLoginId;
    private String memberPassword;
    private String memberEmail;
    private String memberPhone;
    private Date memberBirth;
    private int courseId;
    private boolean memberStatus;
    private boolean memberRole;
    private Date signUpDate;
    private Date signOutDate;

    @Builder
    public MemberInfo(String memberName, String memberLoginId, String memberPassword, String memberEmail, String memberPhone
    , Date memberBirth, int courseId, boolean memberStatus, boolean memberRole, Date signUpDate) {
    }
}
