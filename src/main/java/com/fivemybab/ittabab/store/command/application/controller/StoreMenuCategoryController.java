package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreMenuCategoryDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreMenuCategoryDto;
import com.fivemybab.ittabab.store.command.application.service.StoreMenuCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "MenuCategory", description = "메뉴 카테고리 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu/category")
public class StoreMenuCategoryController {

    private final StoreMenuCategoryService storeMenuCategoryService;

    /* 메뉴 카테고리 등록하기 */
    @Operation(summary = "카테고리 등록")
    @PostMapping
    public ResponseEntity<CreateStoreMenuCategoryDto> CreateStoreMenuCategory(@RequestBody CreateStoreMenuCategoryDto createStoreMenuCategoryDto,
    @AuthenticationPrincipal CustomUserDetails loginUser)
    {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("메뉴 카테고리 등록을 위해서는 로그인이 필요합니다.");
        }

        storeMenuCategoryService.createStoreMenuCategory(createStoreMenuCategoryDto);
        return new ResponseEntity<>(createStoreMenuCategoryDto, HttpStatus.CREATED);
    }

    /* 메뉴 카테고리 수정하기 */
    @Operation(summary = "카테고리 수정")
    @PutMapping("/{categoryId}")
    public ResponseEntity<UpdateStoreMenuCategoryDto> UpdateStoreMenuCategory(@PathVariable Long categoryId, @RequestBody UpdateStoreMenuCategoryDto updateStoreMenuCategoryDto,
        @AuthenticationPrincipal CustomUserDetails loginUser)
    {

        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("메뉴 카테고리 수정을 위해서는 로그인이 필요합니다.");
        }

        storeMenuCategoryService.updateStoreMenuCategory(categoryId, updateStoreMenuCategoryDto);
        return new ResponseEntity<>(updateStoreMenuCategoryDto, HttpStatus.OK);
    }

    /* 메뉴 카테고리 삭제하기 (관리자만) */

    @Operation(summary = "카테고리 삭제(관리자)")
    @DeleteMapping("/admin/{categoryId}")
    public ResponseEntity<Void> DeleteStoreMenuCategory(@PathVariable Long categoryId) {
        storeMenuCategoryService.deleteStoreMenuCategory(categoryId);
        return ResponseEntity.noContent().build();
    }


}
