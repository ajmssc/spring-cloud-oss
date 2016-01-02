package web.accounts;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Hide the access to the microservice inside this local service.
 * 
 * @author Paul Chapman
 */
@Service
public class AccountsRemoteService {

	@Autowired
	protected RestTemplate restTemplate;

	protected String serviceUrl;

	protected Logger logger = Logger.getLogger(AccountsRemoteService.class
			.getName());

	public AccountsRemoteService(String serviceUrl) {
		this.serviceUrl = serviceUrl.startsWith("http") ? serviceUrl
				: "http://" + serviceUrl;
	}

	/**
	 * The RestTemplate works because it uses a custom request-factory that uses
	 * Ribbon to look-up the service to use. This method simply exists to show
	 * this.
	 */
	@PostConstruct
	public void demoOnly() {
		// Can't do this in the constructor because the RestTemplate injection
		// happens afterwards.
		logger.warning("The RestTemplate request factory is "
				+ restTemplate.getRequestFactory());
	}


	@HystrixCommand(fallbackMethod = "defaultFindByNumber")
	public Account findByNumber(String accountNumber) {

		logger.info("findByNumber() invoked: for " + accountNumber);
		return restTemplate.getForObject(serviceUrl + "/accounts/{number}",
				Account.class, accountNumber);
	}

	public Account defaultFindByNumber(String accountNumber) {
		Account account = new Account();
		account.setOwner("Me");
		account.setId(5678);
		account.setNumber("Cool");
		account.setBalance(BigDecimal.valueOf(1231231));
		return account;
	}


	public List<Account> byOwnerContains(String name) {
		logger.info("byOwnerContains() invoked:  for " + name);
		Account[] accounts = null;

		try {
			accounts = restTemplate.getForObject(serviceUrl + "/accounts/owner/{name}", Account[].class, name);
		} catch (HttpClientErrorException e) { // 404
			// Nothing found
		}

		if (accounts == null || accounts.length == 0)
			return null;
		else
			return Arrays.asList(accounts);
	}
}
