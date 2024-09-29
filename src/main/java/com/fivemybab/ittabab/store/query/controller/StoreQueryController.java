package com.fivemybab.ittabab.store.query.controller;

import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreReviewInfoDto;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreMenu;
import com.fivemybab.ittabab.store.query.service.StoreQueryService;
import com.fivemybab.ittabab.store.query.service.StoreReviewQueryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
@Slf4j
public class StoreQueryController {

    private final StoreQueryService storeQueryService;
    private final StoreReviewQueryService storeReviewQueryService;

    /* 전체 가게 목록 조회 */
    @GetMapping("/list")
    public ResponseEntity<List<StoreInfoDto>> storeList() {
        List<StoreInfoDto> storeList = storeQueryService.findStoreList();

        return new ResponseEntity<>(storeList, HttpStatus.OK);
    }

    /* 가게 상세 조회 */
    @GetMapping("/detail/{storeId}")
    public ResponseEntity<StoreInfoDto> storeDetail(@PathVariable Long storeId) {
        StoreInfoDto store = storeQueryService. findStoreByStoreId(storeId);
        return new ResponseEntity<>(store, HttpStatus.OK);
    }


    /* 가게 리뷰 전체 조회 */
    @GetMapping("/review/list")
    public ResponseEntity<List<StoreReviewInfoDto>> storeReviewList() {
        List<StoreReviewInfoDto> storeReviewList = storeReviewQueryService.findStoreReviewList();

        return new ResponseEntity<>(storeReviewList, HttpStatus.OK);
    }

    /* 가게 리뷰 상세 조회 */
    @GetMapping("/review/detail/{reviewId}")
    public ResponseEntity<StoreReviewInfoDto> storeReviewDetail(@PathVariable Long reviewId) {
        StoreReviewInfoDto storeReview = storeReviewQueryService.findStoreReviewById(reviewId);

        return new ResponseEntity<>(storeReview, HttpStatus.OK);
    }


}
