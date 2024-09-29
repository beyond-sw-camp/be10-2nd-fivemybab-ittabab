package com.fivemybab.ittabab.store.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMenuDTO {

    private Long orderId;
    private Long reviewId;
    private Long menuId;

}
