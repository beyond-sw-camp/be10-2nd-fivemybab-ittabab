package com.fivemybab.ittabab.store.command.service;

import com.fivemybab.ittabab.store.command.dto.StoreDTO;
import com.fivemybab.ittabab.store.command.entity.Store;
import com.fivemybab.ittabab.store.command.repository.StoreRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreService {

    private final StoreRepository storeRepository;
    private final ModelMapper modelMapper;

    public StoreService(StoreRepository storeRepository, ModelMapper modelMapper) {
        this.storeRepository = storeRepository;
        this.modelMapper = modelMapper;
    }

    /* 가게 조회 */
    /* 조회 부분은 Mybatis 사용 */
    public List<StoreDTO> findStoreList() {
        List<Store> storeList = storeRepository.findAll(Sort.by("storeId").descending());
        return storeList.stream()
                .map(storeInfo -> modelMapper.map(storeInfo, StoreDTO.class))
                .toList();
    }

    /* 가게 번호로 가게 조회 */
    public StoreDTO findStoreById(int storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(IllegalArgumentException::new);

        return modelMapper.map(store, StoreDTO.class);
    }




    /* 가게 추가 */
    @Transactional
    public void registSchedule(StoreDTO storeDTO) {
        storeRepository.save(modelMapper.map(storeDTO, Store.class));
    }

    /* 가게 정보 수정 */
    @Transactional
    public void modifyStore(StoreDTO storeDTO) {


    }

    /* 가게 삭제 */
    @Transactional
    public void deleteStore(Integer storeId) {
        storeRepository.deleteById(storeId);
    }


}
