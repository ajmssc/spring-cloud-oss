package web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import web.accounts.AccountsRemoteService;

/**
 * Accounts web-server. Works as a microservice client, fetching data from the
 * Account-Service. Uses the Discovery Server (Eureka) to find the microservice.
 *
 * @author Paul Chapman
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCircuitBreaker
@EnableZuulProxy
// Disable component scanner ...
@ComponentScan(useDefaultFilters = false)
public class WebServer {

    /**
     * URL uses the logical name of account-service - upper or lower case,
     * doesn't matter.
     */
    public static final String ACCOUNTS_SERVICE_URL = "http://ACCOUNTS-SERVICE";

    /**
     * Run the application using Spring Boot and an embedded servlet engine.
     *
     * @param args Program arguments - ignored.
     */
    public static void main(String[] args) {
        // Tell server to look for web-server.properties or web-server.yml
        System.setProperty("spring.config.name", "webserver");
        SpringApplication.run(WebServer.class, args);
    }

    /**
     * The AccountService encapsulates the interaction with the micro-service.
     *
     * @return A new service instance.
     */
    @Bean
    public AccountsRemoteService accountsService() {
        return new AccountsRemoteService(ACCOUNTS_SERVICE_URL);
    }

    /**
     * Create the controller, passing it the {@link AccountsRemoteService} to use.
     *
     * @return
     */
    @Bean
    public WebAccountsController accountsController() {
        return new WebAccountsController(accountsService());
    }

    @Bean
    public HomeController homeController() {
        return new HomeController();
    }
}
