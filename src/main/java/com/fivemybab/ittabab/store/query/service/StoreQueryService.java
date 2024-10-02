package com.fivemybab.ittabab.store.query.service;

import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDto;
import com.fivemybab.ittabab.store.command.application.dto.StorePopularInfoDto;
import com.fivemybab.ittabab.store.query.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryService {

    private final StoreMapper storeMapper;

    /* 전체 가게 목록 조회*/
    public List<StoreInfoDto> findStoreList(){
        return storeMapper.findStoreList();
    }

    /* 가게 리뷰가 많은 순대로 조회하되, 후순위로 별점이 많은 순으로 조회 */
    public List<StorePopularInfoDto> findStoreReviewRatingList(){
        return storeMapper.findStoreReviewRatingList();
    }


    /* 특정 가게 정보 조회*/
    public StoreInfoDto findStoreByStoreId(Long id){ return storeMapper.findStoreByStoreId(id); }

}
