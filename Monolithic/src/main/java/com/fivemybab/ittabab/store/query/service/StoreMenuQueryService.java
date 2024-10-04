package com.fivemybab.ittabab.store.query.service;


import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.store.command.application.dto.StoreMenuInfoDto;
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

        List<StoreMenuInfoDto> storeMenuList = storeMenuMapper.findStoreMenuByStoreId(storeId);

        if (storeMenuList.isEmpty()) {
            throw new NotFoundException("해당 가게가 존재하지 않습니다.");
        }

        return storeMenuList;

    }

    /* 메뉴 리뷰가 많은 순대로 조회하되, 후순위로 별점이 높은 순으로 조회 */
    public List<StorePopularMenuInfoDto> findStorePopularMenuByStoreId(Long storeId) {

        /* 해당 가게가 존재하는지 여부 확인 */
        if (storeMenuMapper.findStoreMenuByStoreId(storeId).isEmpty()) {
            throw new NotFoundException("가게가 존재하지 않습니다.");
        }

        List<StorePopularMenuInfoDto> storePopularMenuList = storeMenuMapper.findStorePopularMenuByStoreId(storeId);

        /* 가게 메뉴가 존재하는지 확인 */
        if(storePopularMenuList.isEmpty()) {
            throw new NotFoundException("가게의 메뉴가 존재하지 않습니다.");
        }

        return storePopularMenuList;
    }

    /* 가게 메뉴 상세 조회 */
    public StoreMenuInfoDto findStoreMenuByMenuId(Long storeId, Long menuId) {

        /* 해당 가게가 존재하는지 여부 확인 */
        if (storeMenuMapper.findStoreMenuByStoreId(storeId).isEmpty()) {
            throw new NotFoundException("가게가 존재하지 않습니다.");
        }

        StoreMenuInfoDto storeMenu = storeMenuMapper.findStoreMenuByMenuId(storeId, menuId);

        if(storeMenu == null) {
            throw new NotFoundException("메뉴가 존재하지 않습니다.");
        }

        return storeMenu;

    }

}
