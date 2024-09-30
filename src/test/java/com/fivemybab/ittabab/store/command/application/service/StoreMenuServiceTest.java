package com.fivemybab.ittabab.store.command.application.service;

import com.fivemybab.ittabab.store.command.application.dto.CreateStoreMenuDto;
import com.fivemybab.ittabab.store.query.service.StoreMenuQueryService;
import jakarta.transaction.Transactional;
import org.junit.jupiter.params.provider.Arguments;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class StoreMenuServiceTest {

    @Autowired
    private StoreMenuService storeMenuService;

    @Autowired
    private StoreMenuQueryService storeMenuQueryService;

//    private static Stream<Arguments> getStoreMenuInfo() {
//
//        CreateStoreMenuDto storeMenu = new CreateStoreMenuDto();
//
//
//
//    }




}