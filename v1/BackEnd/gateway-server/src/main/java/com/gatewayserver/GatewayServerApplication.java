package com.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * The Class GatewayServerApplication.
 */
@EnableEurekaClient
@SpringBootApplication
public class GatewayServerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(GatewayServerApplication.class, args);
	}

}