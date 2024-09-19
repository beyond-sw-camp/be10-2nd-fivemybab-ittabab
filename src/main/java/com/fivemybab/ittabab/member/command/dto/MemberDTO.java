package com.fivemybab.ittabab.member.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


/**
 * MemberDTO
 * 회원 정보를 담는 데이터 전송 객체
 */
@Getter
@Setter
@ToString

public class MemberDTO {

    private Long id;
    private String name;
    private String loginId;
    private String email;
    private String phone;
    private String birthDate;
    private boolean status;

}
