package com.fivemybab.ittabab.store.query.controller;

import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDTO;
import com.fivemybab.ittabab.store.command.application.dto.StoreReviewInfoDTO;
import com.fivemybab.ittabab.store.query.service.StoreQueryService;
import com.fivemybab.ittabab.store.query.service.StoreReviewQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreQueryController {

    private final StoreQueryService storeQueryService;
    private final StoreReviewQueryService storeReviewQueryService;

    /* 전체 가게 목록 조회 */
    @GetMapping("/list")
    public String storeList(Model model) {
        List<StoreInfoDTO> storeList = storeQueryService.findStoreList();

        if(!storeList.isEmpty() && storeList.size() > 0) {
            model.addAttribute("storeList", storeList);
        }
        return "store/list";
    }

    /* 가게 상세 조회 */
    @GetMapping("/detail/{storeId}")
    public String storeDetail(@PathVariable Long storeId, Model model) {
        StoreInfoDTO store = storeQueryService.findStoreByStoreId(storeId);
        model.addAttribute("store", store);
        return "store/detail";
    }


    /* 가게 리뷰 전체 조회 */
    @GetMapping("/review/list")
    public String storeReviewList(Model model) {
        List<StoreReviewInfoDTO> storeReviewList = storeReviewQueryService.findStoreReviewList();

        if(!storeReviewList.isEmpty() && storeReviewList.size() > 0) {
            model.addAttribute("storeReviewList", storeReviewList);
        }

        return "store/review/list";
    }

    /* 가게 리뷰 상세 조회 */
    @GetMapping("/review/detail/{reviewId}")
    public String storeReviewDetail(@PathVariable Long reviewId, Model model) {
        StoreReviewInfoDTO storeReview = storeReviewQueryService.findStoreReviewById(reviewId);
        model.addAttribute("storeReview", storeReview);

        return "store/review/detail";
    }


}
