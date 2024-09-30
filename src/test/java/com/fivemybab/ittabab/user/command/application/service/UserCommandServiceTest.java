package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.CreateUserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
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

    private static Stream<Arguments> getUserInfo() {

        CreateUserRequest user = new CreateUserRequest();
        user.setUsername("홍길동");
        user.setLoginId("user11");
        user.setPwd("pass11");
        user.setEmail("test11@gmail.com");
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
}