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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int MenuCategoryId;
    private String MenuCategoryName;

}
