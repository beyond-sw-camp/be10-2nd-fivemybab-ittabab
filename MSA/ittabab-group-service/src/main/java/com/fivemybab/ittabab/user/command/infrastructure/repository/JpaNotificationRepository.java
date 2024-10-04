package com.fivemybab.ittabab.user.command.infrastructure.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.Notification;
import com.fivemybab.ittabab.user.command.domain.repository.NotificationRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaNotificationRepository extends NotificationRepository, JpaRepository<Notification, Long> {
}
