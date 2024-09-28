package com.fivemybab.ittabab.store.command.application.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreReviewInfoDto {

    private Long reviewId;
    private Long storeId;
    private Long userId;
    private String reviewContent;
    private int rating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;



}
