package com.fivemybab.ittabab.store.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class OrderMenuDTO {

    private int orderId;
    private int reviewId;
    private int menuId;

}
