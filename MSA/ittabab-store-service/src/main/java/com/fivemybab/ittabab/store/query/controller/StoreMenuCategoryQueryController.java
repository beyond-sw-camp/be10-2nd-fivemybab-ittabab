package com.fivemybab.ittabab.store.query.controller;

import com.fivemybab.ittabab.store.command.application.dto.StoreMenuCategoryInfoDto;
import com.fivemybab.ittabab.store.query.service.StoreMenuCategoryQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "MenuCategory", description = "메뉴 카테고리 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu/category")
public class StoreMenuCategoryQueryController {

    private final StoreMenuCategoryQueryService storeMenuCategoryQueryService;

    /*  메뉴 카테고리 전체 조회 */
    @Operation(summary = "카테고리 전체 조회")
    @GetMapping("/list")
    public ResponseEntity<List<StoreMenuCategoryInfoDto>> storeMenuCategoryList(){
        List<StoreMenuCategoryInfoDto> menuCategoryList = storeMenuCategoryQueryService.findStoreMenuCategoryList();

        return new ResponseEntity<>(menuCategoryList, HttpStatus.OK);
    }

}
