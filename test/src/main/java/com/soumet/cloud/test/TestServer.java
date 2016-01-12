package com.soumet.cloud.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.logging.Logger;

@EnableDiscoveryClient
@SpringBootApplication
public class TestServer {

	protected Logger logger = Logger.getLogger(TestServer.class.getName());

    final static long version = Math.round(Math.random() * 65000);

	public static void main(String[] args) {
		System.setProperty("spring.config.name", "test");
		System.setProperty("PORT", String.valueOf(version));
        SpringApplication.run(TestServer.class, args);
	}
}
