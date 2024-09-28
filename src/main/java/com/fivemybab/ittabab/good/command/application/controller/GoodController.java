package com.fivemybab.ittabab.good.command.application.controller;

import com.fivemybab.ittabab.good.command.application.dto.GoodRequestDto;
import com.fivemybab.ittabab.good.command.application.service.GoodService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/good")
public class GoodController {

    private final GoodService goodService;

    public GoodController(GoodService goodService){
        this.goodService = goodService;
    }

    // 좋아요 등록
    @PostMapping
    public ResponseEntity<Void> like(@RequestBody GoodRequestDto requestDto, Authentication authentication) {
        goodService.createGood(authentication, requestDto.getTarget(), requestDto.getTargetId());
        return ResponseEntity.ok().build();
    }

    // 좋아요 삭제
    @DeleteMapping
    public ResponseEntity<Void> unlike(@RequestBody GoodRequestDto requestDto, Authentication authentication) {
        goodService.deleteGood(authentication, requestDto.getTarget(), requestDto.getTargetId());
        return ResponseEntity.ok().build();
    }
}
