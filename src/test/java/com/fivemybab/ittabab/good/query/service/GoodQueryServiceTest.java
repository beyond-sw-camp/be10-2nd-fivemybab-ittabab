package com.fivemybab.ittabab.good.query.service;

import com.fivemybab.ittabab.good.command.domain.aggregate.Good;
import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import com.fivemybab.ittabab.good.command.domain.repository.GoodRepository;
import org.junit.jupiter.api.AfterEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GoodQueryServiceTest {

    @Autowired
    private GoodQueryService goodQueryService;

    @Autowired
    private GoodRepository goodRepository;

    @BeforeEach
    void setUp() {
        // 각 테스트 전에 필요한 초기 데이터를 삽입합니다.
        Good good1 = new Good(1L, Target.POST, 10L); // 사용자 ID 1, 게시물 ID 100
        goodRepository.save(good1);
    }

    @AfterEach
    void tearDown() {
        // 각 테스트 후 데이터베이스를 정리합니다.
        goodRepository.deleteAll();
    }

    @Test
    @DisplayName("좋아요가 없는 경우 카운트")
    void countGoods_NoLikes() {
        // Arrange: 특정 타겟과 ID에 대해 좋아요가 없는 경우를 설정합니다.
        Target target = Target.POST;
        Long targetId = 11L; // 좋아요가 없는 ID

        // Act: 카운트 메서드를 호출합니다.
        int count = goodQueryService.countGoods(target, targetId);

        // Assert: 결과를 검증합니다.
        assertEquals(0, count);
    }

    @Test
    @DisplayName("좋아요가 있는 경우 카운트")
    void countGoods_WithLikes() {
        // Arrange: 좋아요가 있는 경우
        Target target = Target.POST;
        Long targetId = 10L; // 좋아요가 있는 ID

        // Act: 카운트 메서드를 호출합니다.
        int count = goodQueryService.countGoods(target, targetId);

        // Assert: 결과를 검증합니다.
        assertEquals(1, count);
    }
}