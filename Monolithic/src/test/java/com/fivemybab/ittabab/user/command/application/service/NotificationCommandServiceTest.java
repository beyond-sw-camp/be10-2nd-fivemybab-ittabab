package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.user.command.application.dto.CreateNotificationRequest;
import com.fivemybab.ittabab.user.command.domain.aggregate.Notification;
import com.fivemybab.ittabab.user.command.domain.repository.NotificationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.stream.Stream;

@SpringBootTest
@Transactional
class NotificationCommandServiceTest {

    @Autowired
    private NotificationCommandService notificationCommandService;

    @Autowired
    private NotificationRepository notificationRepository;

    private static Stream<Arguments> getNotificationInfo() {

        CreateNotificationRequest notificationRequest = new CreateNotificationRequest();
        notificationRequest.setContent("게시글 알림이 등록되었습니다.");
        notificationRequest.setTarget("POST");
        notificationRequest.setTargetId(1L);
        notificationRequest.setUserIdList(Arrays.asList(2L, 3L, 4L));

        return Stream.of(Arguments.arguments(notificationRequest));
    }

    @DisplayName("알림 등록 테스트")
    @ParameterizedTest
    @MethodSource("getNotificationInfo")
    void testCreateNotification(CreateNotificationRequest notificationRequest) {

        Assertions.assertDoesNotThrow(
                () -> notificationCommandService.createNotification(notificationRequest)
        );
    }

    @DisplayName("알림 읽음 테스트")
    @ParameterizedTest
    @ValueSource(longs = {4L})
    void testReadNotification(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId).get();

        Assertions.assertDoesNotThrow(
                () -> notificationCommandService.readNotification(notificationId)
        );

        Assertions.assertTrue(notification.isRead(), "읽음 여부 컬럼이 true가 아닙니다.");
    }
}