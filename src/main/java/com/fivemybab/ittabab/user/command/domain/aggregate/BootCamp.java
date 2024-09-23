package com.fivemybab.ittabab.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "BootCamp")
@NoArgsConstructor(access = AccessLevel.PROTECTED)

public class BootCamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bootId;
    private String bootName;
    private String bootLocation;

    @Builder
    public BootCamp(Long bootId, String bootName, String bootLocation) {
        this.bootId = bootId;
        this.bootName = bootName;
        this.bootLocation = bootLocation;
    }

}