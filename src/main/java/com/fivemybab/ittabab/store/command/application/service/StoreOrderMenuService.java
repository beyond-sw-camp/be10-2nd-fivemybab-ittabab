package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreOrderMenuDto;
import com.fivemybab.ittabab.store.command.application.repository.StoreOrderMenuRepository;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreOrderMenu;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreOrderMenuService {

    private final StoreOrderMenuRepository repository;
    private final ModelMapper modelMapper;

    /* 가게 주문메뉴 추가 */
    @Transactional
    public void createStoreOrderMenu(CreateStoreOrderMenuDto createStoreOrderMenuDto) {
        StoreOrderMenu storeOrderMenu = modelMapper.map(createStoreOrderMenuDto, StoreOrderMenu.class);
        repository.save(storeOrderMenu);
    }

    /* 가게 주문메뉴 삭제 */
    @Transactional
    public void deleteStoreOrderMenu(Long orderId) {
        repository.deleteById(orderId);
    }


}
