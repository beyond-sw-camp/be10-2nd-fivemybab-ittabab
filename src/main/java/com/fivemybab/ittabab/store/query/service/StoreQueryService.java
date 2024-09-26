package com.fivemybab.ittabab.store.query.service;

import com.fivemybab.ittabab.store.command.application.dto.StoreInfoDTO;
import com.fivemybab.ittabab.store.query.mapper.StoreMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreQueryService {

    private final StoreMapper storeMapper;

    public List<StoreInfoDTO> findStoreList(){
        return storeMapper.findStoreList();
    }

    public StoreInfoDTO findStoreByStoreId(Long id){
        return storeMapper.findStoreByStoreId(id);
    }



}
