package com.fivemybab.ittabab.picture.command.application.dto;

import com.fivemybab.ittabab.picture.command.domain.aggregate.Target;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PictureRequest {
    private String pictureUrl;
    private Target target;
    private Long targetId;
}
