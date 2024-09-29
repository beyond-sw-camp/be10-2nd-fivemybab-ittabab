package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreReviewDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreReviewDto;
import com.fivemybab.ittabab.store.command.application.service.StoreReviewService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Tag(name = "Review", description = "가게 리뷰 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/review")
public class StoreReviewController {

    private final StoreReviewService storeReviewService;

    /* 가게 리뷰 등록하기 */
    @PostMapping
    public ResponseEntity<CreateStoreReviewDto> registStoreReview(@RequestBody CreateStoreReviewDto createStoreReviewDto) {
        storeReviewService.registStoreReview(createStoreReviewDto);
        return new ResponseEntity<>(createStoreReviewDto, HttpStatus.CREATED);
    }

    /* 가게 리뷰 수정하기 */
    @PutMapping("/{reviewId}")
    public ResponseEntity<Void> updateStoreReview(@PathVariable Long reviewId, @RequestBody UpdateStoreReviewDto updateStoreReviewDTO) {

        storeReviewService.updateStoreReview(reviewId, updateStoreReviewDTO);
        return ResponseEntity.noContent().build();
    }

    /* 가게 리뷰 삭제하기 */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteStoreReview(@RequestParam Long reviewId) {
        storeReviewService.deleteStoreReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
