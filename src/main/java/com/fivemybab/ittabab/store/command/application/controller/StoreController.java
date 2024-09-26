package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.store.command.application.dto.StoreRegistDTO;
import com.fivemybab.ittabab.store.command.application.dto.StoreReviewRegistDTO;
import com.fivemybab.ittabab.store.command.application.dto.StoreReviewUpdateDTO;
import com.fivemybab.ittabab.store.command.application.dto.StoreUpdateDTO;
import com.fivemybab.ittabab.store.command.application.service.StoreReviewService;
import com.fivemybab.ittabab.store.command.application.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;
    private final StoreReviewService storeReviewService;


    /* 가게 등록하기  */
    @PostMapping
    public ResponseEntity<StoreRegistDTO> registStore(@RequestBody StoreRegistDTO storeRegistDTO) {

        storeService.registStore(storeRegistDTO);
        return new ResponseEntity<>(storeRegistDTO, HttpStatus.CREATED);
    }


    /* 가게 수정하기 */
    @PutMapping("/{storeId}")
    public ResponseEntity<Void> updateStore(@PathVariable Long storeId, @RequestBody StoreUpdateDTO storeUpdateDTO) {
        storeService.updateStore(storeId, storeUpdateDTO);

        return ResponseEntity.noContent().build();
    }


    /* 가게 삭제하기 */
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@RequestParam Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }

    /* 가게 리뷰 등록하기 */
    @PostMapping("/review")
    public ResponseEntity<StoreReviewRegistDTO> registStoreReview(@RequestBody StoreReviewRegistDTO storeReviewRegistDTO) {
        storeReviewService.registStoreReview(storeReviewRegistDTO);
        return new ResponseEntity<>(storeReviewRegistDTO, HttpStatus.CREATED);
    }

    /* 가게 리뷰 수정하기 */
    @PutMapping("/review/{reviewId}")
    public ResponseEntity<Void> updateStoreReview(@PathVariable Long reviewId, @RequestBody StoreReviewUpdateDTO storeReviewUpdateDTO) {

        storeReviewService.updateStoreReview(reviewId, storeReviewUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    /* 가게 리뷰 삭제하기 */
    @DeleteMapping("/review/{reviewId}")
    public ResponseEntity<Void> deleteStoreReview(@RequestParam Long reviewId) {
        storeReviewService.deleteStoreReview(reviewId);
        return ResponseEntity.noContent().build();
    }







}
