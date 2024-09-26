package com.fivemybab.ittabab.store.command.application.dto;

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
    private int menuCategoryCode;

}
