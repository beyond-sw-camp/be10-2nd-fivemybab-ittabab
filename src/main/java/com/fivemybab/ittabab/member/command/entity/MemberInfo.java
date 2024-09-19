package com.fivemybab.ittabab.member.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "member_info")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int memberId; // 회원 ID, 기본키

    private String username; // 회원 이름
    private String email; // 회원 이메일
    private String phoneNumber; // 회원 전화번호
    private LocalDate joinedDate; // 회원 가입 일자
    private String role; // 회원 역할 (예: ADMIN, USER)

    // 생성자
    @Builder
    public MemberInfo(String username, String email, String phoneNumber, LocalDate joinedDate, String role) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.joinedDate = joinedDate;
        this.role = role;
    }

    // 회원 이름 수정 메소드
    public void modifyUsername(String username) {
        this.username = username;
    }

    // 회원 이메일 수정 메소드
    public void modifyEmail(String email) {
        this.email = email;
    }

    // 회원 전화번호 수정 메소드
    public void modifyPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // 회원 역할 수정 메소드
    public void modifyRole(String role) {
        this.role = role;
    }

}
