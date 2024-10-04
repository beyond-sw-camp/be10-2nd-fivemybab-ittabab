package com.fivemybab.ittabab.store.command.application.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class StoreReviewInfoDto {

    private Long reviewId;
    private Long storeId;
    private Long userId;
    private String reviewContent;
    private Integer rating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;



}
