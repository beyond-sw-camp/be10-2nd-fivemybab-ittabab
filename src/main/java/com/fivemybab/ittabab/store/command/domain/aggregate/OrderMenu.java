package com.fivemybab.ittabab.store.command.domain.aggregate;

import jakarta.persistence.*;

@Entity
@Table(name = "order_menu")
public class OrderMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    private Long reviewId;
    private Long menuId;

}
