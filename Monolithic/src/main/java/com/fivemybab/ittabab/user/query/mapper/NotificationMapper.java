package com.fivemybab.ittabab.user.query.mapper;

import com.fivemybab.ittabab.user.query.dto.NotificationDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface NotificationMapper {

    List<NotificationDto> findNotificationList(Long userId);
}
