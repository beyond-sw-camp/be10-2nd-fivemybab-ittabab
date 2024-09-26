package com.fivemybab.ittabab.store.query.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreReviewInfoDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreReviewMapper {

    /* 가게 전체 리뷰 조회 */
    List<StoreReviewInfoDTO> findStoreReviewList();

    /* 특정 가게 리뷰 조회 */
    StoreReviewInfoDTO findStoreReviewById(Long id);
}
