package com.fivemybab.ittabab.good.query.service;


import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodQueryServiceTest {

    @Autowired
    private GoodQueryService goodQueryService;


    @Test
    @DisplayName("좋아요 개수 카운트")
    void countGoods_PostTarget() {
        Long targetId = 1L;
        int count = goodQueryService.countGoods(Target.POST, targetId);
        assertEquals(1, count);
    }


    @Test
    @DisplayName("좋아요가 없는 경우 카운트")
    void countGoods_NoLikes() {
        Long targetId = 2L;
        int count = goodQueryService.countGoods(Target.POST, targetId);
        assertEquals(0, count);
    }
}