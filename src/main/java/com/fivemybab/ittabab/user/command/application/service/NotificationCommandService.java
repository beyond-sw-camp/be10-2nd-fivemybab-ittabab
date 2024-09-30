package com.fivemybab.ittabab.user.command.application.service;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.user.command.application.dto.CreateNotificationRequest;
import com.fivemybab.ittabab.user.command.application.dto.NotificationDto;
import com.fivemybab.ittabab.user.command.domain.aggregate.Notification;
import com.fivemybab.ittabab.user.command.domain.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationCommandService {

    private final ModelMapper modelMapper;
    private final NotificationRepository notificationRepository;

    @Transactional
    public void createNotification(CreateNotificationRequest createNotificationRequest) {

        List<Long> userIdList = createNotificationRequest.getUserIdList();

        NotificationDto notificationDto =
                NotificationDto.builder()
                        .content(createNotificationRequest.getContent())
                        .target(createNotificationRequest.getTarget())
                        .targetId(createNotificationRequest.getTargetId())
                        .build();

        for(Long userId : userIdList) {
            notificationDto.setUserId(userId);
            Notification notification = modelMapper.map(notificationDto, Notification.class);
            notificationRepository.save(notification);
        }
    }

    @Transactional
    public void readNotification(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() -> new NotFoundException("알림을 찾을 수 없습니다. ID: " + notificationId));

        notification.readNotification();

        notificationRepository.save(notification);
    }
}
