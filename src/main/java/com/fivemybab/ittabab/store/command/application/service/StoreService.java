package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.StoreRegistDTO;
import com.fivemybab.ittabab.store.command.application.dto.StoreUpdateDTO;
import com.fivemybab.ittabab.store.command.application.repository.StoreRepository;
import com.fivemybab.ittabab.store.command.domain.aggregate.Store;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreService {


    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;


    /* 가게 추가 */
    @Transactional
    public void registStore(StoreRegistDTO newStore) {
        storeRepository.save(modelMapper.map(newStore, Store.class));
    }

    /* 가게 수정 */
    @Transactional
    public void updateStore(Long storeId, StoreUpdateDTO storeUpdateDTO) {
        Store store =
                storeRepository.findById(storeId)
                        .orElseThrow(()-> new IllegalArgumentException("가게를 찾지 못했습니다."));

        if (storeUpdateDTO.getStoreName() != null) {
            store.modifyStoreName(storeUpdateDTO.getStoreName());
        }

        if (storeUpdateDTO.getStoreLocation() != null) {
            store.modifyStoreLocation(storeUpdateDTO.getStoreLocation());
        }

        if (storeUpdateDTO.getStoreOpenTime() != null) {
            store.modifyStoreOpenTime(storeUpdateDTO.getStoreOpenTime());
        }

        if (storeUpdateDTO.getStoreEndTime() != null) {
            store.modifyStoreEndTime(storeUpdateDTO.getStoreEndTime());
        }

        if (storeUpdateDTO.getStoreWeek() != null) {
            store.modifyStoreWeek(storeUpdateDTO.getStoreWeek());
        }

        if (storeUpdateDTO.getStoreInfo() != null) {
            store.modifyStoreInfo(storeUpdateDTO.getStoreInfo());
        }

        if (storeUpdateDTO.getStoreStatus() != null) {
            store.modifyStoreStatus(store.getStoreStatus());
        }

    }



    /* 가게 삭제 */
    @Transactional
    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }


}
