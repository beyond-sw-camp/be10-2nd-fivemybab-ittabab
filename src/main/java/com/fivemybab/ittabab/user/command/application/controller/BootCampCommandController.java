package com.fivemybab.ittabab.user.command.application.controller;

import com.fivemybab.ittabab.user.command.application.dto.CreateBootCampRequest;
import com.fivemybab.ittabab.user.command.application.dto.UpdateBootCampRequest;
import com.fivemybab.ittabab.user.command.application.service.BootCampCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "BootCamp", description = "부트캠프(훈련 기관) 관련 API")
@RestController
@RequestMapping("/bootcamp")
@RequiredArgsConstructor
public class BootCampCommandController {

    private final BootCampCommandService bootCampCommandService;

    /* 부트캠프(훈련 기관) 등록 */
    @Operation(summary = "부트캠프 등록")
    @PostMapping
    public ResponseEntity<String> createBootCamp(@RequestBody CreateBootCampRequest createBootCampRequest) {

        bootCampCommandService.createBootCamp(createBootCampRequest);

        return ResponseEntity.ok().body("등록 성공");
    }

    /* 부트캠프(훈련 기관) 수정 */
    @Operation(summary = "부트캠프 수정")
    @PutMapping("/{id}")
    public ResponseEntity<String> updateBootcamp(@PathVariable Long id, @RequestBody UpdateBootCampRequest updateBootCampRequest) {

        bootCampCommandService.modifyBootCamp(id, updateBootCampRequest);

        return ResponseEntity.ok().body("수정 성공");
    }

    /* 부트캠프(훈련 기관) 삭제 */
    @Operation(summary = "부트캠프 삭제")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBootcamp(@PathVariable Long id) {

        bootCampCommandService.deleteBootCamp(id);

        return ResponseEntity.noContent().build();
    }
}
