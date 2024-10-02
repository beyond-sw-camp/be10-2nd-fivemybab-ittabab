package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreStatus;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreDto;
import com.fivemybab.ittabab.store.query.service.StoreQueryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreServiceTest {

    @Autowired
    private StoreService storeService;

    @Autowired
    private StoreQueryService storeQueryService;

    private static Stream<Arguments> getStoreInfo() {

        CreateStoreDto store = new CreateStoreDto();
        store.setStoreName("모수");
        store.setStoreLocation("서울특별시 한남동");
        store.setStoreName("09:00:00");
        store.setStoreLocation("18:00:00");
        store.setStoreWeek("월, 화, 수, 목, 금");
        store.setStoreInfo("미슐랭 3스타 같아요");
        store.setStoreStatus(StoreStatus.OPEN);

        return Stream.of(Arguments.of(store));
    }


    @DisplayName("가게 정보 등록 테스트")
    @ParameterizedTest
    @MethodSource("getStoreInfo")
    void testCreateStore(CreateStoreDto newStore, @AuthenticationPrincipal CustomUserDetails loginUser ) {

        Assertions.assertDoesNotThrow(
                () -> storeService.createStore(newStore, loginUser.getUserId())
        );
    }

    private static Stream<Arguments> getModifyStoreInfo() {

        UpdateStoreDto store = new UpdateStoreDto();
        store.setStoreName("모와수");
        store.setStoreLocation("삼성동");
        store.setStoreStatus(StoreStatus.TEMPORARILY_CLOSED);

        return Stream.of(Arguments.arguments(2L, store));
    }

    @DisplayName("가게 정보 수정 테스트")
    @ParameterizedTest
    @MethodSource("getModifyStoreInfo")
    void testUpdateStore(Long storeId, Long userId, UpdateStoreDto modifyInfo) {

        Assertions.assertDoesNotThrow(
                () -> storeService.updateStore(storeId, userId, modifyInfo)
        );
    }

    @DisplayName("가게 정보 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {8L})
    void testDeleteStore(Long storeId, Long userId) {
        Assertions.assertDoesNotThrow(
                () -> storeService.deleteStore(storeId, userId)
        );
    }

}