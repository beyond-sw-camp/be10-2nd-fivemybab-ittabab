package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreFavoriteDto;
import com.fivemybab.ittabab.store.command.application.repository.StoreFavoriteRepository;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreFavorite;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreFavoriteService {

    private final StoreFavoriteRepository repository;
    private final ModelMapper modelMapper;

    /* 가게 즐겨찾기 추가 */
    @Transactional
    public void createStoreFavorite(CreateStoreFavoriteDto newStoreFavorite) {
        StoreFavorite storeFavorite = modelMapper.map(newStoreFavorite, StoreFavorite.class);
        repository.save(storeFavorite);
    }

    /* 가게 즐겨찾기 삭제 */
    @Transactional
    public void deleteStoreFavorite(Long favoriteId) {
        repository.deleteById(favoriteId);
    }



}
