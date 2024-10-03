package com.fivemybab.ittabab.store.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@Table(name = "review")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;
    private Long storeId;
    private Long userId;
    private String reviewContent;
    private Integer rating;
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime updateDate;
    private boolean isBlinded;

    public void setUserId(Long userId) { this.userId = userId; }

    public void modifyReviewRating(Integer rating) {this.rating = rating;}

    public void modifyStoreReviewContent(String newReviewContent) {
        this.reviewContent = newReviewContent;
    }

    public void modifyStoreReviewRating(int newReviewRating) {
        this.rating = newReviewRating;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateDate = LocalDateTime.now(); // 수정 시에 업데이트 시간 변경
    }


}
