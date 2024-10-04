package com.fivemybab.ittabab;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class IttababGroupServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IttababGroupServiceApplication.class, args);
    }

}
