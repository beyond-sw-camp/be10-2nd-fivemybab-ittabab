package com.fivemybab.ittabab.good.command.application.controller;

import com.fivemybab.ittabab.good.command.application.dto.GoodRequestDto;
import com.fivemybab.ittabab.good.command.application.service.GoodCommandService;
import com.fivemybab.ittabab.security.util.CustomUserDetails;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Good", description = "좋아요 관련 API")
@RestController
@RequestMapping("/good")
@RequiredArgsConstructor
public class GoodCommandController {

    private final GoodCommandService goodCommandService;

    /* 좋아요 추가 */
    @Operation(summary = "좋아요 추가")
    @PostMapping
    public ResponseEntity<Void> like(@RequestBody GoodRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails loginUser) {
        goodCommandService.createGood(loginUser.getUserId(), requestDto.getTarget(), requestDto.getTargetId());
        return ResponseEntity.ok().build();
    }

    /* 좋아요 삭제 */
    @Operation(summary = "좋아요 삭제")
    @DeleteMapping
    public ResponseEntity<Void> unlike(@RequestBody GoodRequestDto requestDto, @AuthenticationPrincipal CustomUserDetails loginUser) {
        goodCommandService.deleteGood(loginUser.getUserId(), requestDto.getTarget(), requestDto.getTargetId());
        return ResponseEntity.ok().build();
    }
}
