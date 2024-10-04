package com.fivemybab.ittabab.user.query.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderInfoResponse {

    private Long orderId;
    private Long menuId;
    private String menuName;
    private String menuPrice;
    private String menuCategoryName;
    private String storeName;
    private String reviewContent;
    private Integer rating;

}