package com.fivemybab.ittabab.good.command.domain.repository;

import com.fivemybab.ittabab.good.command.domain.aggregate.Good;
import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.Authentication;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    boolean existsByUserIdAndTargetAndTargetId(Long userId, Target target, Long targetId);

    Optional<Object> findByUserIdAndTargetAndTargetId(Long userId, Target target, Long targetId);
}
