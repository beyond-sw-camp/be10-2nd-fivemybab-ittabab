package com.fivemybab.ittabab.user.query.controller;

import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.user.query.dto.NotificationDto;
import com.fivemybab.ittabab.user.query.service.NotificationQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Notification", description = "알림 관련 API")
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationQueryController {

    private final NotificationQueryService notificationQueryService;

    /* 알림 리스트 조회 */
    @Operation(summary = "알림 리스트 조회")
    @GetMapping("/list")
    public ResponseEntity<List<NotificationDto>> getNotificationList(@AuthenticationPrincipal CustomUserDetails loginUser) {

        Long userId = loginUser.getUserId();   // 현재 로그인된 계정의 userId

        List<NotificationDto> notificationList = notificationQueryService.findNotificationList(userId);

        return new ResponseEntity<>(notificationList, HttpStatus.OK);
    }

}
