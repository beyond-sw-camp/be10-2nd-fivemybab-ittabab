package com.fivemybab.ittabab.good.query.mapper;

import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodMapper {

    int countByTargetAndTargetId(Target target, Long targetId);
}
