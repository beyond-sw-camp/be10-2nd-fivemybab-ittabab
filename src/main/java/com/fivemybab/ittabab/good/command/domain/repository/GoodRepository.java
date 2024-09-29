package com.fivemybab.ittabab.good.command.domain.repository;

import com.fivemybab.ittabab.good.command.domain.aggregate.Good;
import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import com.fivemybab.ittabab.user.command.domain.aggregate.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GoodRepository extends JpaRepository<Good, Long> {
    Optional<Object> findByUserIdAndTargetAndTargetId(Long userId, Target target, Long targetId);

    Long countByTargetAndTargetId(Target target, Long targetId);
}
