package com.fivemybab.ittabab.store.command.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "menu_category")
@Table(name = "menu_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MenuCategory {


    @Id
    @Column(name = "menu_category_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MenuCategoryId;

    @Column(name = "menu_category_name", nullable = false)
    private String MenuCategoryName;

}
