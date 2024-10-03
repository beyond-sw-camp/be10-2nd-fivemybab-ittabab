package com.fivemybab.ittabab.store.query.controller;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.StoreOrderMenuInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreUserOrderMenuInfoDto;
import com.fivemybab.ittabab.store.query.service.StoreMenuCategoryQueryService;
import com.fivemybab.ittabab.store.query.service.StoreOrderMenuQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    public ResponseEntity<List<StoreOrderMenuInfoDto>> StoreOrderMenuList(@AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        List<StoreOrderMenuInfoDto> storeOrderMenuList = storeOrderMenuQueryService.findStoreOrderMenuList(userId);

        return new ResponseEntity<>(storeOrderMenuList, HttpStatus.OK);
    }

    /* 특정 가게 주문 메뉴 조회 */
    @Operation(summary = "특정 가게 주문 메뉴 조회")
    @GetMapping("/list/{storeId}")
    public ResponseEntity<List<StoreUserOrderMenuInfoDto>> storeUserOrderMenuList(@PathVariable Long storeId,
    @AuthenticationPrincipal CustomUserDetails loginUser) {


        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        List<StoreUserOrderMenuInfoDto> storeUserOrderMenuList = storeOrderMenuQueryService.findStoreUserOrderMenuByStoreId(storeId, userId);

        return new ResponseEntity<>(storeUserOrderMenuList, HttpStatus.OK);

    }


}
