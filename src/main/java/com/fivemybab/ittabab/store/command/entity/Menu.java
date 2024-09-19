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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int menuId;
    private int storeId;
    private String menuName;
    private int menuPrice;
    private int menuCategoryId;



}
