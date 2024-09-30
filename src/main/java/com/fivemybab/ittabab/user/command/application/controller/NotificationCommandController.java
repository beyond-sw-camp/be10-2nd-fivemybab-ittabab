package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.CreateNotificationRequest;
import com.fivemybab.ittabab.user.command.application.service.NotificationCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Notification", description = "알림 관련 API")
@RestController
@RequestMapping("/notification")
@RequiredArgsConstructor
public class NotificationCommandController {

    private final NotificationCommandService notificationCommandService;

    /* 알림 등록 */
    @Operation(summary = "알림 등록")
    @PostMapping
    public ResponseEntity<String> createNotification(@RequestBody CreateNotificationRequest createNotificationRequest) {

        notificationCommandService.createNotification(createNotificationRequest);

        return ResponseEntity.ok().body("알림이 등록되었습니다.");
    }

}
