package com.fivemybab.ittabab.store.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreUserOrderMenuInfoDto {

    private Long orderId;
    private Long menuId;
    private String menuName;
    private String menuPrice;
    private String menuCategoryName;
    private String storeName;
    private String reviewContent;
    private Integer rating;

}