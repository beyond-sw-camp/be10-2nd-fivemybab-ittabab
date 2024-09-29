package com.fivemybab.ittabab.store.query.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreFavoriteInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreFavoriteMapper {

    /* 전체 가게 즐겨찾기 조회 */
    List<StoreFavoriteInfoDto> findStoreFavoriteList();

    /* 특정 가게 즐겨찾기 조회 */
    StoreFavoriteInfoDto findStoreFavoriteInfoByFavoriteId(Long id);
}
