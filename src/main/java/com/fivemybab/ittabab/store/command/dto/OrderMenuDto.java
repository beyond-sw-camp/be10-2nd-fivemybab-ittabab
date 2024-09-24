package com.fivemybab.ittabab.store.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMenuDto {

    private Long orderId;
    private Long reviewId;
    private Long menuId;

}
