package com.fivemybab.ittabab.store.command.application.repository;

import com.fivemybab.ittabab.store.command.domain.aggregate.StoreFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreFavoriteRepository extends JpaRepository<StoreFavorite, Long> {
}
