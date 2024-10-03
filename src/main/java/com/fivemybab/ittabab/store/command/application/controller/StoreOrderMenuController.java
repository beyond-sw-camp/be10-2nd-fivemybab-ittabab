package com.fivemybab.ittabab.store.command.application.controller;


import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreOrderMenuDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreOrderMenuInfoDto;
import com.fivemybab.ittabab.store.command.application.service.StoreOrderMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OrderMenu", description = "가게 주문 메뉴 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu/order")
public class StoreOrderMenuController {

    private final StoreOrderMenuService storeOrderMenuService;

    /* 주문 메뉴 등록하기 */
    @Operation(summary = "주문 메뉴 등록 ")
    @PostMapping
    public ResponseEntity<String> CreateStoreOrderMenu(@RequestBody CreateStoreOrderMenuDto createStoreOrderMenuDto,
    @AuthenticationPrincipal CustomUserDetails loginUser)
    {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        storeOrderMenuService.createStoreOrderMenu(createStoreOrderMenuDto, userId);

        return new ResponseEntity<>("주문 메뉴 등록 완료", HttpStatus.CREATED);
    }



    /* 주문 메뉴 삭제하기 (관리자만 가능) */
    @Operation(summary = "주문 메뉴 삭제 (관리자) ")
    @DeleteMapping
    public ResponseEntity<Void> DeleteStoreOrderMenu(@RequestParam Long orderId) {

        storeOrderMenuService.deleteStoreOrderMenu(orderId);
        return ResponseEntity.noContent().build();

    }


}
