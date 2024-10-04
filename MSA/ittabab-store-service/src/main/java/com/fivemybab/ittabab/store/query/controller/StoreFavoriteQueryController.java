package com.fivemybab.ittabab.store.query.controller;
import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.StoreFavoriteInfoDto;
import com.fivemybab.ittabab.store.query.service.StoreFavoriteQueryService;
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

@Tag(name = "StoreFavorite", description = "가게 즐겨찾기 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/favorite")
public class StoreFavoriteQueryController {

    private final StoreFavoriteQueryService storeFavoriteQueryService;

    /* 가게 즐겨찾기 전체 조회 */
    @Operation(summary = "즐겨찾기 전체 조회")
    @GetMapping("/list")
    public ResponseEntity<List<StoreFavoriteInfoDto>> storeFavoriteList(@AuthenticationPrincipal CustomUserDetails loginUser){

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        List<StoreFavoriteInfoDto> storeFavoriteList = storeFavoriteQueryService.findStoreFavoriteList(userId);

        return new ResponseEntity<>(storeFavoriteList, HttpStatus.OK);
    }

    /* 가게 즐겨찾기 상세 조회 */
    @Operation(summary = "즐겨찾기 상세 조회")
    @GetMapping("/detail/{storeId}")
    public ResponseEntity<StoreFavoriteInfoDto> storeFavoriteDetail(@PathVariable Long storeId,
    @AuthenticationPrincipal CustomUserDetails loginUser)
    {

        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }


        Long userId = loginUser.getUserId();
        StoreFavoriteInfoDto storeFavorite = storeFavoriteQueryService.findStoreFavoriteByStoreId(storeId, userId);

        return new ResponseEntity<>(storeFavorite, HttpStatus.OK);
    }



}
