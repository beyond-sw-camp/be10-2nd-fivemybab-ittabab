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
    @Column(name = "store_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int storeId;

    @Column(name = "store_name", nullable = false, columnDefinition = "varchar(300)")
    private String storeName;

    @Column(name = "store_location", nullable = false, columnDefinition = "varchar(300)")
    private String storeLocation;

    @Column(name = "store_open_time")
    private LocalTime storeOpenTime;

    @Column(name = "store_end_time")
    private LocalTime storeEndTime;

    @Column(name = "store_week", columnDefinition = "varchar(300)")
    private String storeWeek;

    @Column(name = "store_info", nullable = false, columnDefinition = "varchar(300)")
    private String storeInfo;

    @Column(name = "store_status")
    @Enumerated(EnumType.STRING)
    private StoreStatus storeStatus; //enum 타입 해결하기

    @Column(name = "member_id")
    private int memberId;



}
