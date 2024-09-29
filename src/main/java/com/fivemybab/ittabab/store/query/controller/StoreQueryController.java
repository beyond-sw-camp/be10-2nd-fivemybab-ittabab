package com.fivemybab.ittabab.store.query.controller;

import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreReviewInfoDto;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreMenu;
import com.fivemybab.ittabab.store.query.service.StoreQueryService;
import com.fivemybab.ittabab.store.query.service.StoreReviewQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@Tag(name = "Store", description = "가게 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store")
public class StoreQueryController {

    private final StoreQueryService storeQueryService;

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


}
