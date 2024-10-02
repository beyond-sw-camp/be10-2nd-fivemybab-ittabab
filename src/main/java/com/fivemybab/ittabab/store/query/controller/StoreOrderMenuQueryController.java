package com.fivemybab.ittabab.store.query.controller;

import com.fivemybab.ittabab.store.command.application.dto.StoreOrderMenuInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreUserOrderMenuInfoDto;
import com.fivemybab.ittabab.store.query.service.StoreMenuCategoryQueryService;
import com.fivemybab.ittabab.store.query.service.StoreOrderMenuQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "OrderMenu", description = "가게 주문 메뉴 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu/order")
public class StoreOrderMenuQueryController {

    private final StoreOrderMenuQueryService storeOrderMenuQueryService;

    /* 전체 주문 메뉴 조회 */
    @Operation(summary = "전체 주문 메뉴 조회")
    @GetMapping("/list")
    public ResponseEntity<List<StoreOrderMenuInfoDto>> StoreOrderMenuList() {
        List<StoreOrderMenuInfoDto> storeOrderMenuList = storeOrderMenuQueryService.findStoreOrderMenuList();

        return new ResponseEntity<>(storeOrderMenuList, HttpStatus.OK);
    }

    /* 특정 유저 주문 메뉴 전체 조회 */
    @Operation(summary = "특정 유저 주문 메뉴 전체 조회")
    @GetMapping("/list/{userId}")
    public ResponseEntity<List<StoreUserOrderMenuInfoDto>> storeUserOrderMenuList(@PathVariable Long userId) {

        List<StoreUserOrderMenuInfoDto> storeUserOrderMenuList = storeOrderMenuQueryService.findStoreUserOrderMenuByUserId(userId);
        return new ResponseEntity<>(storeUserOrderMenuList, HttpStatus.OK);

    }

    /* 특정 리뷰 주문 메뉴 조회 */
    @Operation(summary = "주문메뉴 상세 조회")
    @GetMapping("/detail/{orderId}")
    public ResponseEntity<StoreOrderMenuInfoDto> StoreOrderMenuDetail(@PathVariable Long orderId) {

        StoreOrderMenuInfoDto storeOrderMenu = storeOrderMenuQueryService.findStoreOrderMenuByOrderId(orderId);
        return new ResponseEntity<>(storeOrderMenu, HttpStatus.OK);
    }


}
