package com.fivemybab.ittabab.store.command.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity(name = "review")
@Table(name = "review")
@Getter
@NoArgsConstructor
public class Review {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;
    private int storeId;
    private int memberId;
    private String reviewContent;
    private int rating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;


}
