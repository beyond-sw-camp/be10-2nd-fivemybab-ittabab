package com.fivemybab.ittabab.store.command.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class FavoriteDTO {

    private int favoriteId;
    private int memberId;
    private int storeId;

}
