package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.command.application.dto.UserDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class UserQueryServiceTest {

    @Autowired
    private UserQueryService userQueryService;

    @DisplayName("특정 회원 조회")
    @ParameterizedTest
    @ValueSource(longs = {2L})
    void testUser(Long userId) {

        UserDto user = userQueryService.findById(userId);

        System.out.println(user);

        Assertions.assertDoesNotThrow(
                () -> userQueryService.findById(userId)
        );
    }

    @Test
    @DisplayName("회원 전체 조회")
    void testUserList() {

        List<UserDto> userList = userQueryService.findAll();

        System.out.println(userList);

        Assertions.assertDoesNotThrow(
                () -> userQueryService.findAll()
        );
    }
}