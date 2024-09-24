package com.fivemybab.ittabab.user.query.controller;

import com.fivemybab.ittabab.user.command.application.dto.UserDTO;
import com.fivemybab.ittabab.user.query.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * UserQueryController
 * 이 클래스는 회원 정보 조회
 * MyBatis
 */
@Controller
@RequestMapping("/api/user")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @Autowired
    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id) {
        UserDTO userDTO = userQueryService.findById(id);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userQueryService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
