package com.fivemybab.ittabab.store.query.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreOrderMenuInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreOrderMenuMapper {

    /* 전체 주문 메뉴 조회 */
    List<StoreOrderMenuInfoDto> findStoreOrderMenuList();

    /* 특정 리뷰 주문 메뉴 조회 */
    StoreOrderMenuInfoDto findStoreOrderMenuByOrderId(Long id);

}
