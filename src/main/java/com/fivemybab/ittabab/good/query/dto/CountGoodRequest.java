package com.fivemybab.ittabab.good.query.dto;

import com.fivemybab.ittabab.report.command.domain.aggregate.Target;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CountGoodRequest {

    private Target target;
    private Long targetId;
}