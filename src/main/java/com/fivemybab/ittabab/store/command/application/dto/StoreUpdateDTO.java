package com.fivemybab.ittabab.store.command.application.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreUpdateDTO {

    private String storeName;
    private String storeLocation;
    @Schema(type = "string", example = "09:00", description = "시간은 HH:mm 형식으로 입력하세요.")
    private LocalTime storeOpenTime;
    @Schema(type = "string", example = "18:00", description = "시간은 HH:mm 형식으로 입력하세요.")
    private LocalTime storeEndTime;
    private String storeWeek;
    private String storeInfo;
    private StoreStatus storeStatus;

}
