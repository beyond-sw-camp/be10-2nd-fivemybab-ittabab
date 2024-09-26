package com.fivemybab.ittabab.store.query.service;

import com.fivemybab.ittabab.store.command.application.dto.StoreReviewInfoDTO;
import com.fivemybab.ittabab.store.query.mapper.StoreReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreReviewQueryService {

    private final StoreReviewMapper storeReviewMapper;

    public List<StoreReviewInfoDTO> findStoreReviewList() {
        return storeReviewMapper.findStoreReviewList();
    }

    public StoreReviewInfoDTO findStoreReviewById(Long id) {
        return storeReviewMapper.findStoreReviewById(id);
    }

}
