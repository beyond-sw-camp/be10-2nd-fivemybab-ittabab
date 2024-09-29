package com.fivemybab.ittabab.good.command.application.controller;

import com.fivemybab.ittabab.good.command.application.dto.GoodRequestDto;
import com.fivemybab.ittabab.good.command.application.service.GoodService;
import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
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

    // 좋아요 추가
    @PostMapping
    public ResponseEntity<Void> like(@RequestBody GoodRequestDto requestDto, @RequestParam Long userId) {
        goodService.createGood(userId, requestDto.getTarget(), requestDto.getTargetId());
        return ResponseEntity.ok().build();
    }

    // 좋아요 삭제
    @DeleteMapping
    public ResponseEntity<Void> unlike(@RequestBody GoodRequestDto requestDto, @RequestParam Long userId) {
        goodService.deleteGood(userId, requestDto.getTarget(), requestDto.getTargetId());
        return ResponseEntity.ok().build();
    }

    // 특정 대상의 좋아요 개수 조회
    @GetMapping
    public ResponseEntity<Long> countGoods(@RequestParam Target target, @RequestParam Long targetId) {
        Long count = goodService.countGoods(target, targetId);
        return ResponseEntity.ok(count);
    }
}
