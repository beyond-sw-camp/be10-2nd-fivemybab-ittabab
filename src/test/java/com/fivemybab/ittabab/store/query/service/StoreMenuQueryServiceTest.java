package com.fivemybab.ittabab.store.query.service;

import com.fivemybab.ittabab.store.command.application.dto.StoreMenuInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreMenuQueryServiceTest {

    @Autowired
    private StoreMenuQueryService storeMenuQueryService;

    @DisplayName("가게 메뉴 목록 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {1L})
    public void findStoreMenuByStoreId(Long storeId) {

        Assertions.assertDoesNotThrow(
                () -> {
                    List<StoreMenuInfoDto> storeMenuList = storeMenuQueryService.findStoreMenuByStoreId(storeId);
                    Assertions.assertNotNull(storeMenuList);
                    storeMenuList.forEach(System.out::println);
                }
        );
    }





}