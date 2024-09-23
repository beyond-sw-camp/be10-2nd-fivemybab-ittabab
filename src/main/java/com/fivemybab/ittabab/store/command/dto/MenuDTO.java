package com.fivemybab.ittabab.store.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MenuDTO {

    private Long menuId;
    private Long storeId;
    private String menuName;
    private int menuPrice;
    private Long menuCategoryId;

}
