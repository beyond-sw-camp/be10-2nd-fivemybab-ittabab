package com.fivemybab.ittabab.store.command.entity;

import com.fivemybab.ittabab.store.command.dto.StoreStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalTime;


@Entity(name = "store")
@Table(name = "store")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;
    private String storeName;
    private String storeLocation;
    private LocalTime storeOpenTime;
    private LocalTime storeEndTime;
    private String storeWeek;
    private String storeInfo;
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus;
    private int memberId;



}
