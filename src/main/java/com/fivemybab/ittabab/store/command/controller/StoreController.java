package com.fivemybab.ittabab.store.command.controller;

import com.fivemybab.ittabab.store.command.dto.StoreDTO;
import com.fivemybab.ittabab.store.command.service.StoreService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/store")
public class StoreController {

    private final StoreService storeService;

    public StoreController(StoreService storeService) {
        this.storeService = storeService;
    }

    /* 전체 가게 목록 조회 */
    @GetMapping("/list")
    public String storeList(Model model) {
        List<StoreDTO> storeList = storeService.findStoreList();
        model.addAttribute("storeList", storeList);

        return "store/list";
    }

    /* */

}
