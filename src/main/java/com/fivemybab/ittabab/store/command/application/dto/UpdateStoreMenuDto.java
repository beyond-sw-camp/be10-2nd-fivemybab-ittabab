package com.fivemybab.ittabab.store.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateStoreMenuDto {

    private String menuName;
    private Integer menuPrice;
    private Long MenuCategoryId;

}
