package com.fivemybab.ittabab.good.query.service;

import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import com.fivemybab.ittabab.good.query.mapper.GoodMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GoodQueryService {

    private final GoodMapper goodMapper;

    // 특정 대상의 좋아요 개수 반환
    public int countGoods(Target target, Long targetId) {
        return goodMapper.countByTargetAndTargetId(target, targetId);
    }

}
