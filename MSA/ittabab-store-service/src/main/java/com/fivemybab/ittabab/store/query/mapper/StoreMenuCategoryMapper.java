package com.fivemybab.ittabab.store.query.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreMenuCategoryInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMenuCategoryMapper {

    /* 전체 카테고리 조회*/
    List<StoreMenuCategoryInfoDto> findStoreMenuCategoryList();

}
