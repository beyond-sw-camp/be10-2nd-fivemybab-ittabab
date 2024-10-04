package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.FriendRequestDTO;
import com.fivemybab.ittabab.user.command.application.dto.UpdateFriendRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Stream;

@SpringBootTest
@Transactional
class FriendCommandServiceTest {

    @Autowired
    private FriendCommandService friendCommandService;

    private static Stream<Arguments> getFriendInfo() {

        FriendRequestDTO friendRequest = new FriendRequestDTO();
        friendRequest.setToUserId(5L);

        return Stream.of(Arguments.arguments(2L, friendRequest));
    }

    @DisplayName("친구 요청 테스트")
    @ParameterizedTest
    @MethodSource("getFriendInfo")
    void testSendFriend(Long userId, FriendRequestDTO friendRequest) {

        Assertions.assertDoesNotThrow(
                () -> friendCommandService.sendFriendRequest(userId, friendRequest)
        );
    }

    private static Stream<Arguments> getModifyInfo() {

        UpdateFriendRequest friendRequest = new UpdateFriendRequest();
        friendRequest.setFromUserId(2L);

        return Stream.of(Arguments.arguments(5L, friendRequest));
    }

    @DisplayName("친구 수락 테스트")
    @ParameterizedTest
    @MethodSource("getModifyInfo")
    void testAcceptFriend(Long userId, UpdateFriendRequest updateFriendRequest) {

        Assertions.assertDoesNotThrow(
                () -> friendCommandService.acceptFriendRequest(userId, updateFriendRequest)
        );
    }

    @DisplayName("친구 거절 테스트")
    @ParameterizedTest
    @MethodSource("getModifyInfo")
    void testRejectFriend(Long userId, UpdateFriendRequest updateFriendRequest) {

        Assertions.assertDoesNotThrow(
                () -> friendCommandService.rejectFriendRequest(userId, updateFriendRequest)
        );
    }

    @DisplayName("친구 삭제 테스트")
    @ParameterizedTest
    @CsvSource({"1, 2", "3, 4"})
    void testDeleteFriend(Long userId, Long friendUserId) {

        Assertions.assertDoesNotThrow(
                () -> friendCommandService.deleteFriend(userId, friendUserId)
        );
    }

}