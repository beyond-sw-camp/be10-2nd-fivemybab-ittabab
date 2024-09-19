package com.fivemybab.ittabab.store.command.repository;

import com.fivemybab.ittabab.store.command.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer> {
}
