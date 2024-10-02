package com.fivemybab.ittabab.store.query.mapper;


import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StorePopularInfoDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {

    /* 전체 가게 조회*/
    List<StoreInfoDto> findStoreList();

    /* 가게 리뷰가 많은 순대로 조회하되, 후순위로 별점이 많은 순으로 조회 */
    List<StorePopularInfoDto> findStoreReviewRatingList();

    /* 특정 가게 조회 */
    StoreInfoDto findStoreByStoreId(Long storeId);


}
