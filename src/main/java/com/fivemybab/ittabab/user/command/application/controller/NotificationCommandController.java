package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.CreateNotificationRequest;
import com.fivemybab.ittabab.user.command.application.service.NotificationCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    /* 알림 읽음 */
    @Operation(summary = "알림 읽음")
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<?> readNotification(@PathVariable Long notificationId) {

        try {
            notificationCommandService.readNotification(notificationId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("알림을 읽음 처리하는 중 오류가 발생했습니다: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
