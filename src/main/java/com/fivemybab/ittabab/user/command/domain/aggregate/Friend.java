package com.fivemybab.ittabab.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;
    private Long fromUserId;
    private Long toUserId;
    @Enumerated(EnumType.STRING)
    private FriendStatus friendStatus = FriendStatus.PENDING;

    public void modifyStatus(FriendStatus status) {
        this.friendStatus = status;
    }
}
