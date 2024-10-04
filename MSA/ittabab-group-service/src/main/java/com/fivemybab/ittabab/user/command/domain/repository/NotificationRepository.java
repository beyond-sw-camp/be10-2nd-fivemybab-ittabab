package com.fivemybab.ittabab.user.command.domain.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NotificationRepository{

    Notification save(Notification notification);

    Optional<Notification> findById(Long notificationId);
}
