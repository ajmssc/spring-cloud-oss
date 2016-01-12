package web.accounts;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.logging.Logger;

@Service
public class AccountsRemoteService {

	@Autowired
	protected RestTemplate restTemplate;


    @Autowired
    @Value("${zuul.routes.accounts.serviceId}")
    private String serviceId;

	protected Logger logger = Logger.getLogger(AccountsRemoteService.class.getName());


    @PostConstruct
	public void info() {
		logger.warning("The RestTemplate request factory is " + restTemplate.getRequestFactory());
	}


	@HystrixCommand(fallbackMethod = "offlineFindByNumber")
	public Account findByNumber(String accountNumber) {
		logger.info("findByNumber() invoked: for " + accountNumber);
		return restTemplate.getForObject("http://" + serviceId + "/{number}", Account.class, accountNumber);
	}

	public Account offlineFindByNumber(String accountNumber) {
		Account account = new Account();
		account.setOwner("Me");
		account.setId(5678);
		account.setNumber("Cool");
		account.setBalance(BigDecimal.valueOf(1231231));
		return account;
	}


}
