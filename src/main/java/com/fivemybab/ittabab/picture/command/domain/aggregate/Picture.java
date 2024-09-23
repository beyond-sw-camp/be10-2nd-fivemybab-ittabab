package com.fivemybab.ittabab.picture.command.domain.aggregate;

import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
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
    private Long target_id;

}
