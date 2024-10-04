package com.fivemybab.ittabab.picture.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "picture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Picture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pictureId;
    private String pictureUrl;
    @Enumerated(value = EnumType.STRING)
    private Target target;
    private Long targetId;

    public Picture(String pictureUrl, Target target, Long targetId) {
        this.pictureUrl = pictureUrl;
        this.target = target;
        this.targetId = targetId;
    }
}
