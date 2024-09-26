package com.fivemybab.ittabab.store.command.application.mapper;

import com.fivemybab.ittabab.store.command.application.dto.StoreReviewInfoDTO;


import java.util.List;

public interface ReviewInfoMapper {

    List<StoreReviewInfoDTO> findStoreReviewByStoreId(Long storeId);

}
