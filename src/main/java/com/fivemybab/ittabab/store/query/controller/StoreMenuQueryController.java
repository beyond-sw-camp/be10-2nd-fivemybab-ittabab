package com.fivemybab.ittabab.store.query.controller;


import com.fivemybab.ittabab.store.command.application.dto.StoreMenuInfoDto;
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
    @Operation(summary = "메뉴 전체 조회")
    @GetMapping("/list")
    public ResponseEntity<List<StoreMenuInfoDto>> storeMenuList(Model model) {
        List<StoreMenuInfoDto> storeMenuList = storeMenuQueryService.findStoreMenuList();

        return new ResponseEntity<>(storeMenuList , HttpStatus.OK);
    }

    /* 가게 메뉴 상세 조회 */
    @Operation(summary = "메뉴 상세 조회")
    @GetMapping("/detail/{menuId}")
    public ResponseEntity<StoreMenuInfoDto> storeMenuDetail(@PathVariable Long menuId, Model model) {
        StoreMenuInfoDto storeMenu = storeMenuQueryService.findStoreMenuByMenuId(menuId);

        return new ResponseEntity<>(storeMenu , HttpStatus.OK);
    }




}
