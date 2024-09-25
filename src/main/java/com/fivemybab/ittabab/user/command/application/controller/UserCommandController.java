package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateUserRequest;
import com.fivemybab.ittabab.user.command.application.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;

    /* 회원 가입 기능 */
    @PostMapping("/signup")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest newUser) {

        userCommandService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /* 회원 정보 수정 기능*/
    @PutMapping("/mypage/{userNo}")
    public ResponseEntity<String> modifyUser(@PathVariable Long userNo, @RequestBody UpdateUserRequest updateUserRequest) {

        userCommandService.modifyUser(userNo, updateUserRequest);

        return ResponseEntity.ok().body("회원 정보 수정 완료");
    }

    /* 회원 정보 삭제 */
    @DeleteMapping("/mypage/{userNo}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userNo) {

        userCommandService.deleteUser(userNo);

        return ResponseEntity.ok().body("회원 삭제 완료");
    }


}
