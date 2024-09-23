package com.fivemybab.ittabab.good.command.domain.aggregate;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "picture")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long goodId;
    private Long userId;
    @Enumerated(value = EnumType.STRING)
    private Target target;
    private Long target_id;

}
