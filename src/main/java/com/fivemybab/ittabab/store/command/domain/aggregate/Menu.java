package com.fivemybab.ittabab.store.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "menu")
@Getter
/* AccessLevel.PROTECTED : 기본 생성자의 접근 제한을 통해 부문별한 객체 생성 지양 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private Long storeId;
    private String menuName;
    private int menuPrice;
    private Long menuCategoryId;

}
