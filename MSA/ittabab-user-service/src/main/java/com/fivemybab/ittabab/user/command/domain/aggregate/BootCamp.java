package com.fivemybab.ittabab.user.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    public void modifyName(String bootName) {
        this.bootName = bootName;
    }

    public void modifyLocation(String address) {
        this.address = address;
    }

}
