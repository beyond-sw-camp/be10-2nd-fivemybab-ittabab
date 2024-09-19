package com.fivemybab.ittabab.member.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity(name = "friend")
@Table(name = "friend")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int friendId;

    private int fromMemberId;
    private int toMemberId;
    private boolean accept;

    @Builder
    public Friend(int friendId, int fromMemberId, int toMemberId, boolean accept){
        this.friendId = friendId;
        this.fromMemberId = fromMemberId;
        this.toMemberId = toMemberId;
        this.accept = accept;
    }

    public void modifyfriendId (int friendId){
        this.friendId = friendId;
    }
    public void modifyFromMemberId (int fromMemberId){
        this.fromMemberId = fromMemberId;
    }
    public void modifyToMemberId(int toMemberId){
        this.toMemberId = toMemberId;
    }
    public void modifyAccept (boolean accept){
        this.accept = accept;
    }


}
