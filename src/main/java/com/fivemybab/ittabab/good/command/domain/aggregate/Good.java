package com.fivemybab.ittabab.good.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "good")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodId;
    private Long userId;
    @Enumerated(value = EnumType.STRING)
    private Target target;
    private Long targetId;

    public Good(Long userId, Target target, Long targetId) {
        this.userId = userId;
        this.target = target;
        this.targetId = targetId;
    }
}
