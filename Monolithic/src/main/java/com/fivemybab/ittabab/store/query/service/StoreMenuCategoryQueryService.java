package com.fivemybab.ittabab.store.query.service;

import com.fivemybab.ittabab.store.command.application.dto.StoreMenuCategoryInfoDto;
import com.fivemybab.ittabab.store.query.mapper.StoreMenuCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreMenuCategoryQueryService {

    private final StoreMenuCategoryMapper storeMenuCategoryMapper;

    public List<StoreMenuCategoryInfoDto> findStoreMenuCategoryList() {
        return storeMenuCategoryMapper.findStoreMenuCategoryList();
    }

}
