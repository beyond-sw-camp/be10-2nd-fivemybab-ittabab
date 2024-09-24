package com.fivemybab.ittabab.user.query.controller;

import com.fivemybab.ittabab.user.query.dto.UserDto;
import com.fivemybab.ittabab.user.query.service.UserQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/user")
public class UserQueryController {

    private final UserQueryService userQueryService;

    @Autowired
    public UserQueryController(UserQueryService userQueryService) {
        this.userQueryService = userQueryService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto userDto = userQueryService.findById(id);
        return new ResponseEntity<>(userDto, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userQueryService.findAll();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
