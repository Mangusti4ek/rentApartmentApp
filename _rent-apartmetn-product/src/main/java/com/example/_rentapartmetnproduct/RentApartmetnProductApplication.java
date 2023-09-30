package com.example._rentapartmetnproduct;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class RentApartmetnProductApplication {

	public static void main(String[] args) {
		SpringApplication.run(RentApartmetnProductApplication.class, args);
	}

}
