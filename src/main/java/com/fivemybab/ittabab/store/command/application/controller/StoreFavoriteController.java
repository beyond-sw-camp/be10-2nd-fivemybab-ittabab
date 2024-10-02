package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreFavoriteDto;
import com.fivemybab.ittabab.store.command.application.service.StoreFavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "StoreFavorite", description = "가게 즐겨찾기 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/favorite")
public class StoreFavoriteController {

    private final StoreFavoriteService storeFavoriteService;

    /* 가게 즐겨찾기 등록하기 */
    @Operation(summary = "즐겨찾기 등록")
    @PostMapping
    public ResponseEntity<String> CreateStoreFavorite(@RequestBody CreateStoreFavoriteDto createStoreFavoriteDto, @AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        storeFavoriteService.createStoreFavorite(createStoreFavoriteDto, userId);
        return new ResponseEntity<>("가게 즐겨찾기 완료", HttpStatus.CREATED);
    }

    /* 가게 즐겨찾기 삭제하기 */
    @Operation(summary = "즐겨찾기 삭제")
    @DeleteMapping("/{storeId}")
    public ResponseEntity<String> DeleteStoreFavorite(@RequestParam Long favoriteId, @PathVariable Long storeId,
    @AuthenticationPrincipal CustomUserDetails loginUser)
    {

        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        storeFavoriteService.deleteStoreFavorite(favoriteId, storeId, userId);

        return new ResponseEntity<>("가게 즐겨찾기 삭제 완료", HttpStatus.NO_CONTENT);
    }


}
