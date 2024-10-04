package com.fivemybab.ittabab.store.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StorePopularMenuInfoDto {

    private Long menuId;
    private String menuName;
    private Integer menuPrice;
    private Integer reviewCount;
    private Double averageRating;
    private String categoryName;

}
