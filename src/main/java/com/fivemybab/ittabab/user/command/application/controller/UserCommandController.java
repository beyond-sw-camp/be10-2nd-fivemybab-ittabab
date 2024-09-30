package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.security.util.JwtUtil;
import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateUserRequest;
import com.fivemybab.ittabab.user.command.application.service.UserCommandService;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserRole;
import com.fivemybab.ittabab.user.command.domain.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "User", description = "회원 관련 API")
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserCommandController {

    private final UserCommandService userCommandService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;

    /* 회원 가입 기능 */
    @Operation(summary = "회원 가입")
    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest newUser) {

        userCommandService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 회원 정보 수정 기능*/
    @Operation(summary = "회원 정보 수정")
    @PutMapping("/mypage/{userNo}")
    public ResponseEntity<String> modifyUser(@PathVariable Long userNo, @RequestBody UpdateUserRequest updateUserRequest) {

        userCommandService.modifyUser(userNo, updateUserRequest);

        return ResponseEntity.ok().body("회원 정보 수정 완료");
    }

    /* 회원 정보 삭제 */
    @Operation(summary = "회원 정보 삭제")
    @DeleteMapping("/mypage/{userNo}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userNo) {

        userCommandService.deleteUser(userNo);

        return ResponseEntity.ok().body("회원 삭제 완료");
    }

    /*
    @GetMapping("/admin")
    public ResponseEntity<?> getUserList(@AuthenticationPrincipal CustomUserDetails loginUser) {

        Long userId = loginUser.getUserId();   // 현재 로그인된 계정의 userId;

        return ResponseEntity.ok().build();
    }
    */
}
