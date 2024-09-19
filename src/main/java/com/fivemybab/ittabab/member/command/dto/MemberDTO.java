package com.fivemybab.ittabab.member.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;


/**
 * MemberDTO
 * 회원 정보를 담는 데이터 전송 객체
 */
@Getter
@Setter
@ToString

public class MemberDTO {

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
}
