package com.fivemybab.ittabab.store.query.service;


import com.fivemybab.ittabab.store.command.application.dto.StoreMenuInfoDto;
import com.fivemybab.ittabab.store.query.mapper.StoreMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreMenuQueryService {

    private final StoreMenuMapper storeMenuMapper;

    public List<StoreMenuInfoDto> findStoreMenuByStoreId(Long storeId) {return storeMenuMapper.findStoreMenuByStoreId(storeId);}

    public StoreMenuInfoDto findStoreMenuByMenuId(Long storeId, Long menuId) {return storeMenuMapper.findStoreMenuByMenuId(storeId, menuId); }

}
