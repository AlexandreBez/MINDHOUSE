package com.configserver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * The Class ConfigServerApplication.
 * @version 1.0.0
 * @author Lucas Alexandre Bez Piancoski
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableConfigServer
public class ConfigServerApplication {

	/**
	 * The main method.
	 *
	 * @param args the arguments for spring boot server
	 */
	public static void main(String[] args) {
		SpringApplication.run(ConfigServerApplication.class, args);
	}

}
