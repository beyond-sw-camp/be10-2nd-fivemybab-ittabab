package com.fivemybab.ittabab.store.command.infrastructure.repository;

import com.fivemybab.ittabab.store.command.domain.aggregate.Review;
import com.fivemybab.ittabab.store.command.domain.repository.ReviewRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaReviewRepository extends ReviewRepository, JpaRepository<Review, Long> {
}
