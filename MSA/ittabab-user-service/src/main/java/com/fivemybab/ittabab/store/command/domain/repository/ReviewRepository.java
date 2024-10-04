package com.fivemybab.ittabab.store.command.domain.repository;

import com.fivemybab.ittabab.store.command.domain.aggregate.Review;

import java.util.Optional;

public interface ReviewRepository {

    Optional<Review> findById(Long targetId);
}
