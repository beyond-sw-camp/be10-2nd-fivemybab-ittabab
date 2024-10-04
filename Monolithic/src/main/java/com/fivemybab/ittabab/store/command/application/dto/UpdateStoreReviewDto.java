package com.fivemybab.ittabab.store.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UpdateStoreReviewDto {

    private Long reviewId;
    private String reviewContent;
    private Integer rating;

}
