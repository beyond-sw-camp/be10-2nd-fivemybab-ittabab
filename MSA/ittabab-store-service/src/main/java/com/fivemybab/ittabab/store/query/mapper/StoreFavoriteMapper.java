package com.fivemybab.ittabab.store.query.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreFavoriteInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface StoreFavoriteMapper {

    /* 전체 가게 즐겨찾기 조회 */
    List<StoreFavoriteInfoDto> findStoreFavoriteList(Long id);

    /* 특정 가게 즐겨찾기 조회 */
    Optional<StoreFavoriteInfoDto> findStoreFavoriteByStoreIdAndUserId(Long storeId, Long userId);

}

