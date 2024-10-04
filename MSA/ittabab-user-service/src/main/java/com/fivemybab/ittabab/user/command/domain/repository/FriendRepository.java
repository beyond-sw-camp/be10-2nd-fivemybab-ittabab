package com.fivemybab.ittabab.user.command.domain.repository;

import com.fivemybab.ittabab.user.command.domain.aggregate.Friend;
import com.fivemybab.ittabab.user.command.domain.aggregate.FriendStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    Optional<Friend> findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    Optional<Friend> findByFromUserIdAndToUserIdAndFriendStatus(Long fromUserId, Long toUserId, FriendStatus friendStatus);

    boolean existsByFromUserIdAndToUserId(Long fromUserId, Long toUserId);
}
