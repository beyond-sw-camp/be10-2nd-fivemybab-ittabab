package com.fivemybab.ittabab.store.command.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreOrderMenuInfoDto {

    private Long orderId;
    private Long reviewId;
    private Long menuId;
    private Long userId;

}
