package com.fivemybab.ittabab.store.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor
public class StoreReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private Long storeId;
    private Long userId;
    private String reviewContent;
    private Integer rating;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean isBlinded;

    public void modifyStoreReviewContent(String newReviewContent) {
        this.reviewContent = newReviewContent;
    }

    public void modifyStoreReviewRating(int newReviewRating) {
        this.rating = newReviewRating;
    }



}
