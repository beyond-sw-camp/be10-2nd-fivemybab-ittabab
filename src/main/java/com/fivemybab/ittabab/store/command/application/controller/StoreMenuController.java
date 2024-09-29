package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreMenuDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreMenuDto;
import com.fivemybab.ittabab.store.command.application.service.StoreMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu")
public class StoreMenuController {

    private final StoreMenuService storeMenuService;

    /* 가게 메뉴 등록하기 */
    @PostMapping
    public ResponseEntity<CreateStoreMenuDto> CreateStoreMenu(@RequestBody CreateStoreMenuDto createStoreMenuDto) {
        storeMenuService.createStoreMenu(createStoreMenuDto);
        return new ResponseEntity<>(createStoreMenuDto, HttpStatus.CREATED);
    }

    /* 가게 메뉴 수정하기 */
    @PutMapping("/{menuId}")
    public ResponseEntity<UpdateStoreMenuDto> UpdateStoreMenu(@PathVariable Long menuId, @RequestBody UpdateStoreMenuDto updateStoreMenuDto) {
        storeMenuService.updateStoreMenu(menuId, updateStoreMenuDto);
        return new ResponseEntity<>(updateStoreMenuDto, HttpStatus.OK);
    }


    /* 가게 메뉴 삭제하기 */
    @DeleteMapping("/{menuId}")
    public ResponseEntity<Void> DeleteStoreMenu(@PathVariable Long menuId) {
        storeMenuService.deleteStoreMenu(menuId);
        return ResponseEntity.noContent().build();
    }

}
