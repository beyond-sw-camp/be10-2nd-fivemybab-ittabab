package com.fivemybab.ittabab.store.query.controller;


import com.fivemybab.ittabab.store.command.application.dto.StoreMenuInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StorePopularMenuInfoDto;
import com.fivemybab.ittabab.store.query.service.StoreMenuQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Menu", description = "가게 메뉴 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu")
public class StoreMenuQueryController {

    private final StoreMenuQueryService storeMenuQueryService;

    /* 전체 메뉴 목록 조회 */
    @Operation(summary = "가게 메뉴 전체 조회")
    @GetMapping("/list/{storeId}")
    public ResponseEntity<List<StoreMenuInfoDto>> storeMenuList(@PathVariable Long storeId) {
        List<StoreMenuInfoDto> storeMenuList = storeMenuQueryService.findStoreMenuByStoreId(storeId);

        return new ResponseEntity<>(storeMenuList , HttpStatus.OK);
    }

    /* 메뉴 리뷰가 많은 순대로 조회하되, 후순위로 별점이 높은 순으로 조회 */
    @Operation(summary = "인기 메뉴순으로 전체 조회")
    @GetMapping("/popular/{storeId}")
    public ResponseEntity<List<StorePopularMenuInfoDto>> storePopularMenuList(@PathVariable Long storeId) {
        List<StorePopularMenuInfoDto> storePopularMenuList = storeMenuQueryService.findStorePopularMenuByStoreId(storeId);

        return new ResponseEntity<>(storePopularMenuList , HttpStatus.OK);
    }


    /* 가게 메뉴 상세 조회 */
    @Operation(summary = "가게 메뉴 상세 조회")
    @GetMapping("/detail/{storeId}/{menuId}")
    public ResponseEntity<StoreMenuInfoDto> storeMenuDetail(@PathVariable Long storeId ,@PathVariable Long menuId) {
        StoreMenuInfoDto storeMenu = storeMenuQueryService.findStoreMenuByMenuId(storeId, menuId);

        return new ResponseEntity<>(storeMenu , HttpStatus.OK);
    }




}
