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
    private String bootLocation;

    @Builder
    public BootCamp(Long bootId, String bootName, String bootLocation) {
        this.bootId = bootId;
        this.bootName = bootName;
        this.bootLocation = bootLocation;
    }

    // 'of' 메서드 수정
    public static BootCamp of(String bootName, String bootLocation) {
        return BootCamp.builder()
                .bootName(bootName)
                .bootLocation(bootLocation)
                .build();
    }
}
