package com.fivemybab.ittabab.store.command.application.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CreateStoreReviewDto {

    private Long storeId;
    private Long userId;
    private String reviewContent;
    private int rating;
    private boolean isBlinded;



}
