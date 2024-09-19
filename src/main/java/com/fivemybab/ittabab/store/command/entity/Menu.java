package com.fivemybab.ittabab.store.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "menu")
@Table(name = "menu")
@Getter
/* AccessLevel.PROTECTED : 기본 생성자의 접근 제한을 통해 부문별한 객체 생성 지양 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @Column(name = "menu_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;

    @Column(name = "store_id", nullable = false)
    private int storeId;

    @Column(name = "menu_name", nullable = false, columnDefinition = "varchar(300)")
    private String menuName;

    @Column(name = "menu_price", nullable = false)
    private int menuPrice;

    @Column(name = "menu_category_id", nullable = false)
    private int menuCategoryId;



}
