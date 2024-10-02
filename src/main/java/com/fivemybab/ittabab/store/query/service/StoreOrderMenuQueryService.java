package com.fivemybab.ittabab.store.query.service;


import com.fivemybab.ittabab.store.command.application.dto.StoreOrderMenuInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StoreUserOrderMenuInfoDto;
import com.fivemybab.ittabab.store.query.mapper.StoreOrderMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreOrderMenuQueryService {

    private final StoreOrderMenuMapper storeOrderMenuMapper;

    public List<StoreOrderMenuInfoDto> findStoreOrderMenuList() {
        return storeOrderMenuMapper.findStoreOrderMenuList();
    }

    public StoreOrderMenuInfoDto findStoreOrderMenuByOrderId(Long orderId) {
        return storeOrderMenuMapper.findStoreOrderMenuByOrderId(orderId);
    }

    public List<StoreUserOrderMenuInfoDto> findStoreUserOrderMenuByUserId(Long userId) {
        return storeOrderMenuMapper.findStoreUserOrderMenuByUserId(userId);
    }


}
