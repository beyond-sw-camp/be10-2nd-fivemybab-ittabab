package com.fivemybab.ittabab.store.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStoreMenuDto {

    private Long storeId;
    private String menuName;
    private Integer menuPrice;
    private Long MenuCategoryId;

}
