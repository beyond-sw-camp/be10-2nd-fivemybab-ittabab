package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreReviewDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreReviewDto;
import com.fivemybab.ittabab.store.command.application.service.StoreReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Review", description = "가게 리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/review")
public class StoreReviewController {

    private final StoreReviewService storeReviewService;

    /* 가게 리뷰 등록하기 */
    @Operation(summary = "리뷰 등록")
    @PostMapping
    public ResponseEntity<String> registStoreReview(@RequestBody CreateStoreReviewDto createStoreReviewDto, @AuthenticationPrincipal CustomUserDetails loginUser) {


        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        storeReviewService.registStoreReview(createStoreReviewDto, userId);
        return new ResponseEntity<>("리뷰 등록 완료", HttpStatus.CREATED);
    }

    /* 가게 리뷰 수정하기 */
    @Operation(summary = "리뷰 수정")
    @PutMapping
    public ResponseEntity<String> updateStoreReview(@RequestBody UpdateStoreReviewDto updateStoreReviewDTO, @AuthenticationPrincipal CustomUserDetails loginUser) {

        // 로그인되지 않았거나, userId가 null인 경우 예외 처리
        if (loginUser == null || loginUser.getUserId() == null) {
            throw new NotFoundException("로그인이 필요합니다.");
        }

        Long userId = loginUser.getUserId();
        storeReviewService.updateStoreReview(userId ,updateStoreReviewDTO);
        return new ResponseEntity<>("리뷰 수정 완료", HttpStatus.OK);
    }

    /* 가게 리뷰 삭제하기 */
    @Operation(summary = "리뷰 삭제")
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteStoreReview(@RequestParam Long reviewId) {
        storeReviewService.deleteStoreReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
