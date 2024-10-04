package com.fivemybab.ittabab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IttababBoardServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IttababBoardServiceApplication.class, args);
    }

}
