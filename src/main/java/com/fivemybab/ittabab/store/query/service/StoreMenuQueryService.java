package com.fivemybab.ittabab.store.query.service;


import com.fivemybab.ittabab.store.command.application.dto.StoreMenuInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StorePopularInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StorePopularMenuInfoDto;
import com.fivemybab.ittabab.store.query.mapper.StoreMenuMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreMenuQueryService {

    private final StoreMenuMapper storeMenuMapper;

    /* 가게 전체 메뉴 조회 */
    public List<StoreMenuInfoDto> findStoreMenuByStoreId(Long storeId) {
        return storeMenuMapper.findStoreMenuByStoreId(storeId);
    }

    /* 메뉴 리뷰가 많은 순대로 조회하되, 후순위로 별점이 높은 순으로 조회 */
    public List<StorePopularMenuInfoDto> findStorePopularMenuByStoreId(Long storeId) {
        return storeMenuMapper.findStorePopularMenuByStoreId(storeId);
    }

    /* 가게 메뉴 상세 조회 */
    public StoreMenuInfoDto findStoreMenuByMenuId(Long storeId, Long menuId) {
        return storeMenuMapper.findStoreMenuByMenuId(storeId, menuId);
    }

}
