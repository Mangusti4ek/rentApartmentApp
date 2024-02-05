package com.example._rentapigetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RentApiGetwayApplication {

    public static void main(String[] args) {
        SpringApplication.run(RentApiGetwayApplication.class, args);
    }

}
