package com.fivemybab.ittabab.group.command.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "group_member")
public class GroupMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int groupMemberId;
    private int memberId;
    private int groupId;
}
