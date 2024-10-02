package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreFavoriteDto;
import com.fivemybab.ittabab.store.command.application.service.StoreFavoriteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "StoreFavorite", description = "가게 즐겨찾기 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/favorite")
public class StoreFavoriteController {

    private final StoreFavoriteService storeFavoriteService;

    /* 가게 즐겨찾기 등록하기 */
    @Operation(summary = "즐겨찾기 등록")
    @PostMapping
    public ResponseEntity<CreateStoreFavoriteDto> CreateStoreFavorite(@RequestBody CreateStoreFavoriteDto createStoreFavoriteDto) {
        storeFavoriteService.createStoreFavorite(createStoreFavoriteDto);
        return new ResponseEntity<>(createStoreFavoriteDto, HttpStatus.CREATED);
    }

    /* 가게 즐겨찾기 삭제하기 */
    @Operation(summary = "즐겨찾기 삭제")
    @DeleteMapping("/{favoriteId}")
    public ResponseEntity<Void> DeleteStoreFavorite(@RequestParam Long favoriteId) {
        storeFavoriteService.deleteStoreFavorite(favoriteId);
        return ResponseEntity.noContent().build();
    }


}
