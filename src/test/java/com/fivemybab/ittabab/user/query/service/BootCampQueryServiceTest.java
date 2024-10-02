package com.fivemybab.ittabab.user.query.service;

import com.fivemybab.ittabab.user.query.dto.BootCampDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class BootCampQueryServiceTest {

    @Autowired
    private BootCampQueryService bootCampQueryService;

    @Test
    @DisplayName("부트캠프 전체 조회")
    void testBCList() {

        List<BootCampDto> notificationList = bootCampQueryService.findBootCampList();

        System.out.println(notificationList);

        Assertions.assertDoesNotThrow(
                () -> bootCampQueryService.findBootCampList()
        );
    }
}