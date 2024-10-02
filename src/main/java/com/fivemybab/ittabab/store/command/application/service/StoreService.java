package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreDto;
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
    public void createStore(CreateStoreDto newStore) {
        storeRepository.save(modelMapper.map(newStore, Store.class));
    }

    /* 가게 수정 */
    @Transactional
    public void updateStore(Long storeId, UpdateStoreDto updateStoreDto) {
        Store store =
                storeRepository.findById(storeId)
                        .orElseThrow(()-> new IllegalArgumentException("가게를 찾지 못했습니다."));

        if (updateStoreDto.getStoreName() != null) {
            store.modifyStoreName(updateStoreDto.getStoreName());
        }

        if (updateStoreDto.getStoreLocation() != null) {
            store.modifyStoreLocation(updateStoreDto.getStoreLocation());
        }

        if (updateStoreDto.getStoreOpenTime() != null) {
            store.modifyStoreOpenTime(updateStoreDto.getStoreOpenTime());
        }

        if (updateStoreDto.getStoreEndTime() != null) {
            store.modifyStoreEndTime(updateStoreDto.getStoreEndTime());
        }

        if (updateStoreDto.getStoreWeek() != null) {
            store.modifyStoreWeek(updateStoreDto.getStoreWeek());
        }

        if (updateStoreDto.getStoreInfo() != null) {
            store.modifyStoreInfo(updateStoreDto.getStoreInfo());
        }

        if (updateStoreDto.getStoreStatus() != null) {

            String storeStatus = updateStoreDto.getStoreStatus().toString();
            store.modifyStoreStatus(storeStatus);

        }

    }



    /* 가게 삭제 */
    @Transactional
    public void deleteStore(Long storeId) {
        storeRepository.deleteById(storeId);
    }


}
