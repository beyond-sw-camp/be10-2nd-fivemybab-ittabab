package com.fivemybab.ittabab.group.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
@Table(name = "group_user")
public class GroupUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupUserId;
    private Long userId;
    private Long groupId;
}
