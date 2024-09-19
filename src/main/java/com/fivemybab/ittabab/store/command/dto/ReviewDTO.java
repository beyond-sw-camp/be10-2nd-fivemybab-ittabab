package com.fivemybab.ittabab.store.command.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class ReviewDTO {

    private int reviewId;
    private int storeId;
    private int memberId;
    private String reviewContent;
    private int rating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;



}
