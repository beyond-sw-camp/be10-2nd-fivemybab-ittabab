package com.fivemybab.ittabab.store.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "favorite")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StoreFavorite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long favoriteId;
    private Long userId;
    private Long storeId;

    public void setUserId(Long userId) {this.userId = userId;}


}
