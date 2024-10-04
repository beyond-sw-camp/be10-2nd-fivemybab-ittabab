package com.fivemybab.ittabab.store.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreMenuInfoDto {

    private Long menuId;
    private Long storeId;
    private String menuName;
    private Integer menuPrice;
    private Integer menuCategoryId;

}
