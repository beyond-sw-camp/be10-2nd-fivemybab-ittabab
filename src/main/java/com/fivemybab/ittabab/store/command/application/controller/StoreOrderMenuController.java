package com.fivemybab.ittabab.store.command.application.controller;


import com.fivemybab.ittabab.store.command.application.dto.CreateStoreOrderMenuDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreOrderMenuInfoDto;
import com.fivemybab.ittabab.store.command.application.service.StoreOrderMenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "OrderMenu", description = "가게 주문 메뉴 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/store/menu/order")
public class StoreOrderMenuController {

    private final StoreOrderMenuService storeOrderMenuService;

    /* 주문 메뉴 등록하기 */
    @Operation(summary = "주문 메뉴 등록 ")
    @PostMapping
    public ResponseEntity<CreateStoreOrderMenuDto> CreateStoreOrderMenu(@RequestBody CreateStoreOrderMenuDto createStoreOrderMenuDto) {
        storeOrderMenuService.createStoreOrderMenu(createStoreOrderMenuDto);

        return new ResponseEntity<>(createStoreOrderMenuDto, HttpStatus.CREATED);
    }



    /* 주문 메뉴 삭제하기 */
    @Operation(summary = "주문 메뉴 삭제")
    @DeleteMapping
    public ResponseEntity<Void> DeleteStoreOrderMenu(@RequestParam Long orderId) {
        storeOrderMenuService.deleteStoreOrderMenu(orderId);
        return ResponseEntity.noContent().build();

    }


}
