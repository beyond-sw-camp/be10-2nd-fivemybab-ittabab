package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.UserDTO;
import com.fivemybab.ittabab.user.command.application.service.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class UserCommandController {

    private final UserCommandService userCommandService;

    /* 회원 가입 기능 */
    @PostMapping("/user")
    public ResponseEntity<Void> createUser(@RequestBody CreateUserRequest newUser) {

        userCommandService.createUser(newUser);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

//    @PostMapping("/create")
//    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
//        UserDTO createdUser = userCommandService.createUser(userDTO);
//        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
//    }
//
//    @PutMapping("/update/{id}")
//    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
//        UserDTO updatedUser = userCommandService.updateUser(id, userDTO);
//        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
//    }
//
//    @DeleteMapping("/delete/{id}")
//    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
//        userCommandService.deleteUser(id);
//        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }
}
