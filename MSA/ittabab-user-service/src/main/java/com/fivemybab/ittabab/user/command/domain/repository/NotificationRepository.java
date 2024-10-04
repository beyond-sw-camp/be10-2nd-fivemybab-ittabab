package com.fivemybab.ittabab.user.command.domain.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}
