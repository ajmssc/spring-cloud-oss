package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableZuulProxy
@ComponentScan
// Disable component scanner ...
//@ComponentScan(useDefaultFilters = false)
public class WebServer {



    public static void main(String[] args) {
        System.setProperty("spring.config.name", "webserver");
        SpringApplication.run(WebServer.class, args);
    }

}
