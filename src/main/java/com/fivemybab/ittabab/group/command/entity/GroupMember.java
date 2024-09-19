package com.fivemybab.ittabab.group.command.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_member")
public class GroupMember {
    @Id
    private int groupMemberId;
    private int memberId;
    private int groupId;
}
