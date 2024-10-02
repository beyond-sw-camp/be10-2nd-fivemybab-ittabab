package com.fivemybab.ittabab.store.query.controller;

import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StorePopularInfoDto;
import com.fivemybab.ittabab.store.query.service.StoreQueryService;
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

@Tag(name = "Store", description = "가게 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreQueryController {

    private final StoreQueryService storeQueryService;

    /* 전체 가게 목록 조회 */
    @Operation(summary = "가게 전체 조회")
    @GetMapping("/list")
    public ResponseEntity<List<StoreInfoDto>> storeList() {
        List<StoreInfoDto> storeList = storeQueryService.findStoreList();

        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }

    /* 가게 리뷰가 많은 순대로 조회하되, 후순위로 별점이 많은 순으로 조회 */

    @Operation(summary = "가게 인기순으로 조회(리뷰, 별점 내림차순)")
    @GetMapping("/popular")
    public ResponseEntity<List<StorePopularInfoDto>> storeReviewRatingList() {
        List<StorePopularInfoDto> storeList = storeQueryService.findStoreReviewRatingList();

        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }



    /* 가게 상세 조회 */
    @Operation(summary = "가게 상세 조회")
    @GetMapping("/detail/{storeId}")
    public ResponseEntity<StoreInfoDto> storeDetail(@PathVariable Long storeId) {
        StoreInfoDto store = storeQueryService. findStoreByStoreId(storeId);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }


}
