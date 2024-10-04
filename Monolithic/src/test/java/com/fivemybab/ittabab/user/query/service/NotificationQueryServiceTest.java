package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.query.dto.NotificationDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class NotificationQueryServiceTest {

    @Autowired
    private NotificationQueryService notificationQueryService;

    @DisplayName("알림 리스트 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {2L})
    void testNotificationList(Long userId) {

        List<NotificationDto> notificationList = notificationQueryService.findNotificationList(userId);

        System.out.println(notificationList);

        Assertions.assertDoesNotThrow(
                () -> notificationQueryService.findNotificationList(userId)
        );
    }
}