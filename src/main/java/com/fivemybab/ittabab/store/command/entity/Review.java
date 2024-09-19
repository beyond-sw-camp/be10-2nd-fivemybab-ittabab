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
    @Column(name = "review_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int reviewId;

    @Column(name = "store_id", nullable = false)
    private int storeId;

    @Column(name = "member_id", nullable = false)
    private int memberId;

    @Column(name = "review_content" , nullable = false)
    private String reviewContent;

    @Column(name = "rating", nullable = false)
    private int rating;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "is_blinded", nullable = false)
    private boolean isBlinded;


}
