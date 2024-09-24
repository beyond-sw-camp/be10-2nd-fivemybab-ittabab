package com.fivemybab.ittabab.store.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteDto {

    private Long favoriteId;
    private Long userId;
    private Long storeId;

}
