package com.businessServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The main class for the Business Inteligence Server application.
 * 
 * @author Lucas Alexandre
 * @version 1.0.0
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class BusinesInteligenceServerApplication {

	/**
	 * The entry point of the Business Inteligence Server application.
	 *
	 * @param args The command line arguments.
	 */
	public static void main(String[] args) {
		SpringApplication.run(BusinesInteligenceServerApplication.class, args);
	}

}
