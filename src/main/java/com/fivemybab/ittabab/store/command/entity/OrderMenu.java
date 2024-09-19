package com.fivemybab.ittabab.store.command.entity;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class OrderMenu {

    @Id
    @Column(name = "order_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderId;

    @Column(name = "review_id", nullable = false)
    private int reviewId;

    @Column(name = "menu_id", nullable = false)
    private int menuId;


}
