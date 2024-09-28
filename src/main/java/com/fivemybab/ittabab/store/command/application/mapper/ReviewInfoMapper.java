package com.fivemybab.ittabab.store.command.application.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreReviewInfoDto;


import java.util.List;

public interface ReviewInfoMapper {

    List<StoreReviewInfoDto> findStoreReviewByStoreId(Long storeId);

}
