package com.fivemybab.ittabab.good.query.service;

import com.fivemybab.ittabab.good.command.domain.aggregate.Good;
import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import com.fivemybab.ittabab.good.command.domain.repository.GoodRepository;
import com.fivemybab.ittabab.good.query.mapper.GoodMapper;
import org.junit.jupiter.api.AfterEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class GoodQueryServiceTest {

    private GoodQueryService goodQueryService;

    @BeforeEach
    void setUp() {

        goodQueryService = new GoodQueryService(new GoodMapper() {
            @Override
            public int countByTargetAndTargetId(Target target, Long targetId) {
                if (target == Target.POST && targetId == 1L) {
                    return 5;
                }
                return 0;
            }
        });
    }

    @Test
    @DisplayName("좋아요 개수 카운트")
    void countGoods_PostTarget() {
        Long targetId = 1L;
        int count = goodQueryService.countGoods(Target.POST, targetId);
        assertEquals(5, count);  // POST, targetId 1L인 경우 5개로 가정
    }


    @Test
    @DisplayName("좋아요가 없는 경우 카운트")
    void countGoods_NoLikes() {
        Long targetId = 2L;
        int count = goodQueryService.countGoods(Target.POST, targetId);
        assertEquals(0, count);
    }
}