package com.fivemybab.ittabab.store.query.service;
import com.fivemybab.ittabab.exception.NotFoundException;
import com.fivemybab.ittabab.store.command.application.dto.StoreFavoriteInfoDto;
import com.fivemybab.ittabab.store.query.mapper.StoreFavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFavoriteQueryService {

    private final StoreFavoriteMapper storeFavoriteMapper;

    public List<StoreFavoriteInfoDto> findStoreFavoriteList(Long userId) {
        return storeFavoriteMapper.findStoreFavoriteList(userId);
    }

    public StoreFavoriteInfoDto findStoreFavoriteByStoreId(Long storeId, Long userId){

        return storeFavoriteMapper.findStoreFavoriteByStoreIdAndUserId(storeId, userId)
                .orElseThrow(() -> new NotFoundException("해당 가게 즐겨찾기가 존재하지 않습니다."));
    }

}
