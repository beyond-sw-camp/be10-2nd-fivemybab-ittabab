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

    /* 전체 주문 리스트 */
    public List<StoreOrderMenuInfoDto> findStoreOrderMenuList(Long userId) {
        return storeOrderMenuMapper.findStoreOrderMenuList(userId);
    }

    /* 특정 가게 주문 리스트 */
    public List<StoreUserOrderMenuInfoDto> findStoreUserOrderMenuByStoreId(Long storeId ,Long userId) {
        return storeOrderMenuMapper.findStoreUserOrderMenuByStoreId(storeId, userId);
    }


}
