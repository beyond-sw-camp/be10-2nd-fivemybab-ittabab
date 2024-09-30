package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateUserRequest;
import com.fivemybab.ittabab.user.query.service.UserQueryService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class UserCommandServiceTest {

    @Autowired
    private UserCommandService userCommandService;

    @Autowired
    private UserQueryService userQueryService;

    private static Stream<Arguments> getUserInfo() {

        CreateUserRequest user = new CreateUserRequest();
        user.setUsername("홍길동");
        user.setLoginId("user12");
        user.setPwd("pass12");
        user.setEmail("test12@gmail.com");
        user.setPhone("01012312323");
        user.setBirth(LocalDate.parse("2000-01-02"));
        user.setCourseId(1L);

        return Stream.of(Arguments.arguments(user));
    }

    @DisplayName("회원 가입 테스트")
    @ParameterizedTest
    @MethodSource("getUserInfo")
    void testSignUp(CreateUserRequest newUser) {

        Assertions.assertDoesNotThrow(
                () -> userCommandService.createUser(newUser)
        );
    }

    private static Stream<Arguments> getModifyInfo() {

        UpdateUserRequest user = new UpdateUserRequest();
        user.setPwd("pass12");
        user.setPhone("12345678910");

        return Stream.of(Arguments.arguments(2L, user));
    }

    @DisplayName("회원 정보 수정 테스트")
    @ParameterizedTest
    @MethodSource("getModifyInfo")
    void testModifyUser(Long userNo, UpdateUserRequest modifyInfo) {

        Assertions.assertDoesNotThrow(
                () -> userCommandService.modifyUser(userNo, modifyInfo)
        );
    }

    @DisplayName("회원 정보 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {10L})
    void testDeleteUser(Long userId) {

        Assertions.assertDoesNotThrow(
                () -> userCommandService.deleteUser(userId)
        );
    }
}