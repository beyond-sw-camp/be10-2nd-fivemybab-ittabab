package com.fivemybab.ittabab.picture.command.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "picture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pictureId;
    private String pictureUrl;
    private int storeId;
    private int menuId;
    private int reviewId;
    private int postId;

}
