package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreMenuDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreMenuDto;
import com.fivemybab.ittabab.store.command.application.service.StoreMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Menu", description = "가게 메뉴 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu")
public class StoreMenuController {

    private final StoreMenuService storeMenuService;

    /* 가게 메뉴 등록하기 */
    @Operation(summary = "메뉴 등록")
    @PostMapping
    public ResponseEntity<String> CreateStoreMenu(@RequestBody CreateStoreMenuDto createStoreMenuDto, @AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        storeMenuService.createStoreMenu(createStoreMenuDto);
        return new ResponseEntity<>("메뉴 등록 완료", HttpStatus.CREATED);
    }

    /* 가게 메뉴 수정하기 */
    @Operation(summary = "메뉴 수정")
    @PutMapping("/{menuId}")
    public ResponseEntity<UpdateStoreMenuDto> UpdateStoreMenu(@PathVariable Long menuId, @RequestBody UpdateStoreMenuDto updateStoreMenuDto,
    @AuthenticationPrincipal CustomUserDetails loginUser)
    {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        storeMenuService.updateStoreMenu(menuId, updateStoreMenuDto);
        return new ResponseEntity<>(updateStoreMenuDto, HttpStatus.OK);
    }


    /* 가게 메뉴 삭제하기 */
    @Operation(summary = "메뉴 삭제")
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> DeleteStoreMenu(@PathVariable Long menuId, @AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        storeMenuService.deleteStoreMenu(menuId);
        return ResponseEntity.noContent().build();
    }

}
