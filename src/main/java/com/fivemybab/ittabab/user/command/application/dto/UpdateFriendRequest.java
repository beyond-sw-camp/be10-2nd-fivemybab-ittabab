package com.fivemybab.ittabab.user.command.application.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateFriendRequest {

    private Long fromUserId;

    public UpdateFriendRequest() {}

    public UpdateFriendRequest(Long fromUserId) {
        this.fromUserId = fromUserId;
    }
}
