package com.fivemybab.ittabab.store.command.domain.aggregate;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu_category")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreMenuCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuCategoryId;
    private String menuCategoryName;

    public void modifyMenuCategoryName(String menuCategoryName) {
        this.menuCategoryName = menuCategoryName;
    }
}
