package com.fivemybab.ittabab.user.infrastructure.client;

import com.fivemybab.ittabab.user.infrastructure.config.FeignClientConfig;
import com.fivemybab.ittabab.user.query.dto.OrderInfoResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "store-service", url="localhost:8000", configuration = FeignClientConfig.class)
public interface StoreServiceClient {

    @GetMapping("/store-service/store/menu/order/list/{storeId}")
    List<OrderInfoResponse> storeUserOrderMenuList(@PathVariable Long storeId, Long userId);
}
