package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.exception.NotFoundException;
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

        if (!storeRepository.existsByStoreId(storeId)) {
            throw new NotFoundException("해당 가게가 존재하지 않습니다.");
        }

        Store store =
                storeRepository.findByStoreIdAndUserId(storeId, updateStoreDto.getUserId())
                        .orElseThrow(()-> new IllegalArgumentException("타인이 등록한 가게는 수정할 수 없습니다."));

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

            String status = updateStoreDto.getStoreStatus().toString();
            store.modifyStoreStatus(status);
        }

    }



    /* 가게 삭제 */
    @Transactional
    public void deleteStore(Long storeId, Long userId) {

        if (!storeRepository.existsByStoreId(storeId)) {
            throw new NotFoundException("해당 가게가 존재하지 않습니다.");
        }


        // 사용자 ID로 가게를 찾고, 가게가 존재하지 않거나 사용자가 등록한 가게가 아닐 경우 예외 발생
        Store store = storeRepository.findByStoreIdAndUserId(storeId, userId)
                .orElseThrow(() -> new IllegalArgumentException("타인이 등록한 가게는 삭제할 수 없습니다."));

        // 가게 삭제
        storeRepository.delete(store);
    }




}
