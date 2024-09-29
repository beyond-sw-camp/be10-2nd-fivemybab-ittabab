package com.fivemybab.ittabab.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long friendId;
    private Long fromUserId;
    private Long toUserId;
    @Enumerated(EnumType.STRING)
    private FriendStatus friendStatus = FriendStatus.PENDING;

    public void modifyStatus(String status) {
        switch (status){
            case "accept" -> this.friendStatus = FriendStatus.ACCEPTED;
            case "reject" -> this.friendStatus = FriendStatus.REJECTED;
        }
    }
}
