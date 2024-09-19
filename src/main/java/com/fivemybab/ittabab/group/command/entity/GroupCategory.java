package com.fivemybab.ittabab.group.command.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "group_category_id ")
public class GroupCategory {
    @Id
    private int groupCategoryId;
    private String groupCategoryName;
}
