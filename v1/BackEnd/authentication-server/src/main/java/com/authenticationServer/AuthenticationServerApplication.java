package com.authenticationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * The main class for the Authentication Server application.
 * @author Lucas Alexandre Bez Piancoski
 * @version 1.0.0
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class AuthenticationServerApplication {

    /**
     * The entry point of the Authentication Server application.
     *
     * @param args The command line arguments.
     */
	public static void main(String[] args) {
		SpringApplication.run(AuthenticationServerApplication.class, args);
	}
	
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder()
	{
		return new BCryptPasswordEncoder();
	}

}
