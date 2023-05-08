package com.accountmanagementserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountManagementServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountManagementServerApplication.class, args);
	}

}
