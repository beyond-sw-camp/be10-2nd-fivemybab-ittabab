package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.*;
import com.fivemybab.ittabab.store.query.service.StoreMenuQueryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreMenuServiceTest {

    @Autowired
    private StoreMenuService storeMenuService;

    @Autowired
    private StoreMenuQueryService storeMenuQueryService;

    private static Stream<Arguments> getStoreMenuInfo() {

        CreateStoreMenuDto storeMenu = new CreateStoreMenuDto();
        storeMenu.setStoreId(1L);
        storeMenu.setMenuName("김치찌개");
        storeMenu.setMenuPrice(9000);
        storeMenu.setMenuCategoryId(1L);

        return Stream.of(Arguments.of(storeMenu));
    }

    @DisplayName("가게 메뉴 등록 테스트")
    @ParameterizedTest
    @MethodSource("getStoreMenuInfo")
    void testCreateStoreMenu(CreateStoreMenuDto newMenu) {

        Assertions.assertDoesNotThrow(
                () -> storeMenuService.createStoreMenu(newMenu)
        );
    }

    private static Stream<Arguments> getModifyStoreMenuInfo() {

        UpdateStoreMenuDto menu = new UpdateStoreMenuDto();
        menu.setMenuName("된장찌개");
        menu.setMenuPrice(7000);

        return Stream.of(Arguments.arguments(2L, menu));
    }

    @DisplayName("가게 메뉴 수정 테스트")
    @ParameterizedTest
    @MethodSource("getModifyStoreMenuInfo")
    void testUpdateStore(Long menuId, UpdateStoreMenuDto modifyInfo) {

        Assertions.assertDoesNotThrow(
                () -> storeMenuService.updateStoreMenu(menuId, modifyInfo)
        );
    }

    @DisplayName("가게 메뉴 삭제 테스트")
    @ParameterizedTest
    @ValueSource(longs = {3L})
    void testDeleteStoreMenu(Long menuId) {
        Assertions.assertDoesNotThrow(
                () -> storeMenuService.deleteStoreMenu(menuId)
        );
    }


}