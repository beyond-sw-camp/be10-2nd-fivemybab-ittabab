package com.fivemybab.ittabab.user.query.controller;

import com.fivemybab.ittabab.user.query.dto.BootCampDto;
import com.fivemybab.ittabab.user.query.service.BootCampQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "BootCamp", description = "부트캠프(훈련 기관) 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/bootcamp")
public class BootCampQueryController {

    private final BootCampQueryService bootCampQueryService;

    /* 부트캠프(훈련 기관) 리스트 전체 조회 */
    @Operation(summary = "부트캠프 전체 조회")
    @GetMapping("/list")
    public ResponseEntity<List<BootCampDto>> getAllBootCamps() {

        List<BootCampDto> bootCampList = bootCampQueryService.findBootCampList();

        return new ResponseEntity<>(bootCampList, HttpStatus.OK);
    }

}
