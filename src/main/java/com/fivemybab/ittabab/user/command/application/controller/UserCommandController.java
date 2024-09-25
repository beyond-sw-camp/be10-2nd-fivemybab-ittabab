package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
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
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest newUser) {

        userCommandService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
