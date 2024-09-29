package com.fivemybab.ittabab.store.query.controller;
import com.fivemybab.ittabab.store.command.application.dto.StoreFavoriteInfoDto;
import com.fivemybab.ittabab.store.query.service.StoreFavoriteQueryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Favorite", description = "가게 즐겨찾기 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/favorite")
public class StoreFavoriteQueryController {

    private final StoreFavoriteQueryService storeFavoriteQueryService;

    /* 전체 가게 즐겨찾기 목록 조회 */
    @GetMapping("/list")
    public ResponseEntity<List<StoreFavoriteInfoDto>> storeFavoriteList(){
        List<StoreFavoriteInfoDto> storeFavoriteList = storeFavoriteQueryService.findStoreFavoriteList();

        return new ResponseEntity<>(storeFavoriteList, HttpStatus.OK);
    }

    /* 가게 즐겨찾기 상세 조회 */
    @GetMapping("/detail/{favoriteId}")
    public ResponseEntity<StoreFavoriteInfoDto> storeFavoriteDetail(@PathVariable Long favoriteId){
        StoreFavoriteInfoDto storeFavorite = storeFavoriteQueryService.findStoreFavoriteByFavoriteId(favoriteId);

        return new ResponseEntity<>(storeFavorite, HttpStatus.OK);
    }



}
