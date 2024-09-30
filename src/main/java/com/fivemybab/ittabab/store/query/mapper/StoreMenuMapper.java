package com.fivemybab.ittabab.store.query.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreMenuInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMenuMapper {

    /* 가게 전체 메뉴 조회 */
    List<StoreMenuInfoDto> findStoreMenuByStoreId(Long storeId);

    /* 가게 메뉴 상세 조회 */
    StoreMenuInfoDto findStoreMenuByMenuId(Long storeId, Long menuId);


}
