package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreDto;
import com.fivemybab.ittabab.store.command.application.service.StoreService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Store", description = "가게 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    /* 가게 등록하기  */
    @Operation(summary = "가게 등록")
    @PostMapping
    public ResponseEntity<String> createStore(@RequestBody CreateStoreDto createStoreDto, @AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();

        storeService.createStore(createStoreDto, userId);
        return new ResponseEntity<>("가게 등록 완료", HttpStatus.CREATED);

    }

    /* 가게 수정하기 */
    @Operation(summary = "가게 수정")
    @PutMapping("/{storeId}")
    public ResponseEntity<String> updateStore(@PathVariable Long storeId, @RequestBody UpdateStoreDto updateStoreDto, @AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        storeService.updateStore(storeId, userId ,updateStoreDto);

        return new ResponseEntity<>("가게 수정 완료", HttpStatus.OK);
    }



    /* 가게 삭제하기 (관리자만) */

    @Operation(summary = "가게 삭제")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> deleteStore(@RequestParam Long storeId,  @AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        storeService.deleteStore(storeId, userId);

        return new ResponseEntity<>("가게 삭제 완료", HttpStatus.NO_CONTENT);
    }

}
