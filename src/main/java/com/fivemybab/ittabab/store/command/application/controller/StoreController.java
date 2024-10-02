package com.fivemybab.ittabab.store.command.application.controller;

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
    public ResponseEntity<CreateStoreDto> createStore(@RequestBody CreateStoreDto createStoreDto, @AuthenticationPrincipal CustomUserDetails loginUser) {

        storeService.createStore(createStoreDto, loginUser.getUserId());
        return new ResponseEntity<>(createStoreDto, HttpStatus.CREATED);

    }


    /* 가게 수정하기 */
    @Operation(summary = "가게 수정")
    @PutMapping("/{storeId}")
    public ResponseEntity<Void> updateStore(@PathVariable Long storeId, @RequestBody UpdateStoreDto updateStoreDto) {
        storeService.updateStore(storeId, updateStoreDto);

        return ResponseEntity.noContent().build();
    }


    /* 가게 삭제하기 (관리자만) */
    @Operation(summary = "가게 삭제")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@RequestParam Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }

}
