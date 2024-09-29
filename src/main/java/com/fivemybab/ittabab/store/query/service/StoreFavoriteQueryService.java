package com.fivemybab.ittabab.store.query.service;
import com.fivemybab.ittabab.store.command.application.dto.StoreFavoriteInfoDto;
import com.fivemybab.ittabab.store.query.mapper.StoreFavoriteMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreFavoriteQueryService {

    private final StoreFavoriteMapper storeFavoriteMapper;

    public List<StoreFavoriteInfoDto> findStoreFavoriteList() {
        return storeFavoriteMapper.findStoreFavoriteList();
    }

    public StoreFavoriteInfoDto findStoreFavoriteByFavoriteId(Long favoriteId){
        return storeFavoriteMapper.findStoreFavoriteInfoByFavoriteId(favoriteId);}

}
