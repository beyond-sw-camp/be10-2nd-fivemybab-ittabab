package com.fivemybab.ittabab.store.command.application.repository;

import com.fivemybab.ittabab.store.command.domain.aggregate.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
