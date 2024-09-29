package com.fivemybab.ittabab.store.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
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

    public void modifyStoreStatus(StoreStatus newStoreStatus) {
        this.storeStatus = newStoreStatus;
    }


}
