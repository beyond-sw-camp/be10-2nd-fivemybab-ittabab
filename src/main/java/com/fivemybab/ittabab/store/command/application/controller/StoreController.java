package com.fivemybab.ittabab.store.command.application.controller;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreDto;
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

    /* 가게 등록하기  */
    @PostMapping
    public ResponseEntity<CreateStoreDto> registStore(@RequestBody CreateStoreDto createStoreDto) {

        storeService.registStore(createStoreDto);
        return new ResponseEntity<>(createStoreDto, HttpStatus.CREATED);
    }


    /* 가게 수정하기 */
    @PutMapping("/{storeId}")
    public ResponseEntity<Void> updateStore(@PathVariable Long storeId, @RequestBody UpdateStoreDto updateStoreDto) {
        storeService.updateStore(storeId, updateStoreDto);

        return ResponseEntity.noContent().build();
    }


    /* 가게 삭제하기 */
    @DeleteMapping("/{storeId}")
    public ResponseEntity<Void> deleteStore(@RequestParam Long storeId) {
        storeService.deleteStore(storeId);
        return ResponseEntity.noContent().build();
    }

}
