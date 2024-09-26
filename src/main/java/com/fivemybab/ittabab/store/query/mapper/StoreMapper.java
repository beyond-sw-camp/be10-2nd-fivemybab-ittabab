package com.fivemybab.ittabab.store.query.mapper;


import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    /* 전체 가게 조회*/
    List<StoreInfoDTO> findStoreList();

    /* 특정 가게 조회 */
    StoreInfoDTO findStoreByStoreId(Long storeId);


}
