package com.fivemybab.ittabab.user.query.controller;

import com.fivemybab.ittabab.user.query.dto.BootCampDto;
import com.fivemybab.ittabab.user.query.service.BootCampQueryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bootcamp")
public class BootCampQueryController {

    private final BootCampQueryService bootCampQueryService;

    /* 부트캠프(훈련 기관) 리스트 전체 조회 */
    @GetMapping("/list")
    public List<BootCampDto> getAllBootCamps() {
        return bootCampQueryService.findBootCampList();
    }

}
