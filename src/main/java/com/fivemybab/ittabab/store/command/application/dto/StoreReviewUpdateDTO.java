package com.fivemybab.ittabab.store.command.application.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class StoreReviewUpdateDTO {

    private String reviewContent;
    private int rating;

}
