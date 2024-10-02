package com.fivemybab.ittabab.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "bootcamp")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class BootCamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bootId;
    private String bootName;
    private String address;
    private double latitude;
    private double longitude;

    public void modifyName(String bootName) {
        this.bootName = bootName;
    }

    public void modifyLocation(String address) {
        this.address = address;
    }

}
