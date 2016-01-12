package com.soumet.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.logging.Logger;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountsServer {

	protected Logger logger = Logger.getLogger(AccountsServer.class.getName());

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "accounts");
		System.setProperty("PORT", String.valueOf(Math.round(Math.random() * 65000)));
		SpringApplication.run(AccountsServer.class, args);
	}
}
