package com.fivemybab.ittabab.good.query.mapper;

import com.fivemybab.ittabab.good.query.dto.CountGoodRequest;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface GoodMapper {

    int countByTargetAndTargetId(CountGoodRequest request);
}
