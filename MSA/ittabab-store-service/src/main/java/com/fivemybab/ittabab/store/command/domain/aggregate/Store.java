package com.fivemybab.ittabab.store.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Entity
@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long storeId;
    private String storeName;
    private String storeLocation;
    private LocalTime storeOpenTime;
    private LocalTime storeEndTime;
    private String storeWeek;
    private String storeInfo;
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus = StoreStatus.OPEN;
    private Long userId;

    public void setUserId(Long userId) {this.userId = userId;}

    public void modifyStoreName(String newStoreName) {
        this.storeName = newStoreName;
    }

    public void modifyStoreLocation(String newStoreLocation) {
        this.storeLocation = newStoreLocation;
    }

    public void modifyStoreOpenTime(LocalTime newStoreOpenTime) {
        this.storeOpenTime = newStoreOpenTime;
    }

    public void modifyStoreEndTime(LocalTime newStoreEndTime) {
        this.storeEndTime = newStoreEndTime;
    }

    public void modifyStoreWeek(String newStoreWeek) {
        this.storeWeek = newStoreWeek;
    }

    public void modifyStoreInfo(String newStoreInfo) {
        this.storeInfo = newStoreInfo;
    }

    public void modifyStoreStatus(String storeStatus) {
        switch (storeStatus) {

            case "OPEN" -> this.storeStatus = StoreStatus.OPEN;
            case "CLOSED" -> this.storeStatus = StoreStatus.CLOSED;
            case "TEMPORARILY_CLOSED" -> this.storeStatus = StoreStatus.TEMPORARILY_CLOSED;
            case "PERMANENTLY_CLOSED" -> this.storeStatus = StoreStatus.PERMANENTLY_CLOSED;

        }
    }

}
