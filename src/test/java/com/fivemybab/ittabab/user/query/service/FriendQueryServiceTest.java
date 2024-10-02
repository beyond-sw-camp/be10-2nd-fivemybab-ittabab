package com.fivemybab.ittabab.user.query.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class FriendQueryServiceTest {

    @Autowired
    private FriendQueryService friendQueryService;

    @DisplayName("친구 요청 조회")
    @ParameterizedTest
    @ValueSource(longs = {3L})
    void testFindCourseByBC(Long userId) {

        List<Long> friendRequests = friendQueryService.findFriendRequests(userId);

        System.out.println(friendRequests);

        Assertions.assertDoesNotThrow(
                () -> friendQueryService.findFriendRequests(userId)
        );
    }

    @DisplayName("친구 목록 조회")
    @ParameterizedTest
    @ValueSource(longs = {2L})
    void testCourseList(Long userId) {

        List<Long> friendList = friendQueryService.findFriendList(userId);

        System.out.println(friendList);

        Assertions.assertDoesNotThrow(
                () -> friendQueryService.findFriendList(userId)
        );
    }
}