package com.fivemybab.ittabab.group.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
@Table(name = "group_category_id")
public class GroupCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long groupCategoryId;
    private String groupCategoryName;
}
