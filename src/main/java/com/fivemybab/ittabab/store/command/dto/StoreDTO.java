package com.fivemybab.ittabab.store.command.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@ToString
public class StoreDTO {

    private int storeId;
    private String storeName;
    private String storeLocation;
    private LocalTime storeOpenTime;
    private LocalTime storeEndTime;
    private String storeWeek;
    private String storeInfo;
    private StoreStatus storeStatus; //enum 타입 해결하기
    private int memberId;


}
