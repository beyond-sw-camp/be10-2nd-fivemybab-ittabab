package com.fivemybab.ittabab.store.command.application.repository;

import com.fivemybab.ittabab.store.command.domain.aggregate.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Long> {
    // 사용자 ID와 가게 ID로 가게를 찾는 메소드
    Optional<Store> findByStoreIdAndUserId(Long storeId, Long userId);

    // 해당 가게가 존재하는지 확인
    boolean existsByStoreId(Long storeId);

    // 사용자 ID와 가게 ID로 가게를 삭제하는 메소드
    void deleteByStoreIdAndUserId(Long storeId, Long userId);
}
