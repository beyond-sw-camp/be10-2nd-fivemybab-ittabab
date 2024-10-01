package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.query.dto.NotificationDto;
import com.fivemybab.ittabab.user.query.mapper.NotificationMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationQueryService {

    private final NotificationMapper notificationMapper;

    public List<NotificationDto> findNotificationList(Long userId) {
        return notificationMapper.findNotificationList(userId);
    }
}
