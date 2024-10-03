package com.fivemybab.ittabab.store.command.application.repository;

import com.fivemybab.ittabab.store.command.domain.aggregate.StoreOrderMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreOrderMenuRepository extends JpaRepository<StoreOrderMenu, Long> {

    Optional<StoreOrderMenu> findByOrderIdAndUserId(Long orderId, Long userId);

}
