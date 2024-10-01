package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreFavoriteDto;
import com.fivemybab.ittabab.store.command.application.dto.CreateStoreMenuCategoryDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreMenuCategoryDto;
import com.fivemybab.ittabab.store.command.application.dto.UpdateStoreMenuDto;
import com.fivemybab.ittabab.store.command.application.repository.StoreMenuCategoryRepository;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreFavorite;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreMenu;
import com.fivemybab.ittabab.store.command.domain.aggregate.StoreMenuCategory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StoreMenuCategoryService {

    private final StoreMenuCategoryRepository repository;
    private final ModelMapper modelMapper;

    /* 메뉴 카테고리 추가 */
    @Transactional
    public void createStoreMenuCategory(CreateStoreMenuCategoryDto newMenuCategory) {
        StoreMenuCategory storeMenuCategory = modelMapper.map(newMenuCategory, StoreMenuCategory.class);
        repository.save(storeMenuCategory);
    }

    /* 메뉴 카테고리 수정 */
    @Transactional
    public void updateStoreMenuCategory(Long categoryId , UpdateStoreMenuCategoryDto updateMenuCategory) {
        StoreMenuCategory menuCategory = repository.findById(categoryId)
                .orElseThrow(()-> new IllegalArgumentException("메뉴를 찾지 못했습니다."));

        if (updateMenuCategory.getMenuCategoryName() != null) {
            menuCategory.modifyMenuCategoryName(updateMenuCategory.getMenuCategoryName());
        }

    }


    /* 가게 카테고리 삭제 */
    @Transactional
    public void deleteStoreMenuCategory(Long categoryId) {
        repository.deleteById(categoryId);
    }



}
