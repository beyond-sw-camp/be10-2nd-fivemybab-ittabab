package com.fivemybab.ittabab.good.query.controller;

import com.fivemybab.ittabab.good.command.domain.aggregate.Target;
import com.fivemybab.ittabab.good.query.service.GoodQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Good", description = "좋아요 관련 API")
@RestController
@RequestMapping("/good")
@RequiredArgsConstructor
public class GoodQueryController {

    private final GoodQueryService goodQueryService;

    /* 특정 대상의 좋아요 개수 조회 */
    @Operation(summary = "좋아요 개수 조회")
    @GetMapping("/{target}/{targetId}")
    public ResponseEntity<Integer> countGoods(@PathVariable("target") Target target, @PathVariable("targetId") Long targetId) {

        int count = goodQueryService.countGoods(target, targetId);
        return ResponseEntity.ok(count);
    }
}
