package com.fivemybab.ittabab.store.query.service;


import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StoreQueryServiceTest {

    @Autowired
    private StoreQueryService storeQueryService;

    @DisplayName("전체 가게 목록 조회 테스트")
    @Test
    public void findStoreList() {

        Assertions.assertDoesNotThrow(
                () -> {
                    List<StoreInfoDto> StoreList = storeQueryService.findStoreList();

                    // 결과 검증
                    // 여기서 db에 데이터 추가, 삭제의 변동이 일어날 경우 테스트 통과 x
                    Assertions.assertNotNull(StoreList);
                    Assertions.assertEquals(4, StoreList.size());
                    Assertions.assertEquals("test01", StoreList.get(0).getStoreName());
                    Assertions.assertEquals("테스트04", StoreList.get(1).getStoreName());

                    StoreList.forEach(System.out::println);

                }
        );

    }


    @DisplayName("특정 가게 목록 조회 테스트")
    @ParameterizedTest
    @ValueSource(longs = {7L})
    public void findStoreByStoreId(Long storeId) {

        Assertions.assertDoesNotThrow(() -> {

            StoreInfoDto foundStore = storeQueryService.findStoreByStoreId(storeId);
            Assertions.assertNotNull(foundStore);
            System.out.println(foundStore);

        });
    }







}