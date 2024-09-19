package com.fivemybab.ittabab.store.command.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "favorite")
@Table(name = "favorite")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Favorite {

    @Id
    @Column(name = "favorite_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int favoriteId;

    @Column(name = "member_id", nullable = false)
    private int memberId;

    @Column(name = "store_id", nullable = false)
    private int storeId;

}
