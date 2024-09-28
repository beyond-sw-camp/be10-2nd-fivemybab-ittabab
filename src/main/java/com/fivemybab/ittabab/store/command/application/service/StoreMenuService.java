package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreMenuDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreMenuDto;
import com.fivemybab.ittabab.store.command.application.repository.StoreMenuRepository;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreMenu;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuService {

    private final StoreMenuRepository repository;
    private final ModelMapper modelMapper;

    /* 가게 메뉴 추가 */
    @Transactional
    public void createStoreMenu(CreateStoreMenuDto newStoreMenu) {
        StoreMenu storeMenu = modelMapper.map(newStoreMenu, StoreMenu.class);
        repository.save(storeMenu);
    }

    /* 가게 메뉴 수정 */
    @Transactional
    public void updateStoreMenu(Long menuId ,UpdateStoreMenuDto updateStoreMenuDto) {
        StoreMenu storeMenu = repository.findById(menuId)
                .orElseThrow(()-> new IllegalArgumentException("메뉴를 찾지 못했습니다."));

        if(updateStoreMenuDto.getMenuName() != null) {
            storeMenu.modifyStoreMenuName(updateStoreMenuDto.getMenuName());
        }

        if (updateStoreMenuDto.getMenuPrice() != null) {
            storeMenu.modifyStoreMenuPrice(updateStoreMenuDto.getMenuPrice());
        }

        if (updateStoreMenuDto.getMenuCategoryId() != null) {
            storeMenu.modifyStoreMenuCategoryId(updateStoreMenuDto.getMenuCategoryId());
        }

    }


    /* 가게 메뉴 삭제 */
    @Transactional
    public void deleteStoreMenu(Long menuId) {
        repository.deleteById(menuId);
    }

}
