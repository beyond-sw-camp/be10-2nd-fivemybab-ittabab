package com.fivemybab.ittabab.user.command.application.controller;

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

    /* 관리자 페이지 접속 */
    @Operation(summary = "관리자 페이지 접속")
    @GetMapping("/admin")
    public ResponseEntity<?> getUserList(HttpServletRequest request) {

        // Request Header 에 담아준 Authorization 을 가져와서
        String authorization = request.getHeader("Authorization");

        // accessToken 을 꺼내주는 방식
        String accessToken = authorization.substring(7);

        // accessToken 에 있는 유저 권한을 파싱해서 가져옴
        UserRole userRole = jwtUtil.getRole(accessToken);
        log.info("userRole : {}", userRole);

        // accessToken 에 있는 userId을 파싱해서 가져옴
        String userId = jwtUtil.getUserId(accessToken);
        log.info("userId : {}", userId);

        // ADMIN 관리자가 아니면 실행할 수 없는 api
        if (userRole == UserRole.ADMIN) {
            // userId 해당하는 User 정보를 조회합니다.
            List<UserInfo> allUsers = userRepository.findAll();

            // 회원을 찾은 경우 200 OK 응답과 함께 User 정보를 반환합니다.
            return ResponseEntity.ok(allUsers);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("관리자페이지에서, 관리자 권한으로만 회원 전체에 대한 조회가 가능합니다.!");
        }
    }
}
