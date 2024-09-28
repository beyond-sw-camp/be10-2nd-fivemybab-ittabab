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
public class StoreMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long menuId;
    private Long storeId;
    private String menuName;
    private Integer menuPrice;
    private Long menuCategoryId;

    public void modifyStoreMenuName(String menuName) {
        this.menuName = menuName;
    }

    public void modifyStoreMenuPrice(int menuPrice) {
        this.menuPrice = menuPrice;
    }

    public void modifyStoreMenuCategoryId(Long menuCategoryId) {
        this.menuCategoryId = menuCategoryId;
    }
}
