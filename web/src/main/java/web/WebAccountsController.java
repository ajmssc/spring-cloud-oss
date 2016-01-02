package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import web.accounts.Account;
import web.accounts.AccountsRemoteService;

import java.util.List;
import java.util.logging.Logger;

/**
 * Client controller, fetches Account info from the microservice via
 * {@link web.accounts.AccountsRemoteService}.
 * 
 * @author Paul Chapman
 */
@Controller
@RequestMapping("/accounts")
public class WebAccountsController {

	@Autowired
	protected AccountsRemoteService accountsService;

	protected Logger logger = Logger.getLogger(WebAccountsController.class
			.getName());

	public WebAccountsController(AccountsRemoteService accountsService) {
		this.accountsService = accountsService;
	}

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("accountNumber", "searchText");
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String goHome() {
		return "OK";
	}

	@RequestMapping("/{accountNumber}")
	@ResponseBody
	public Account byNumber(Model model,
			@PathVariable("accountNumber") String accountNumber) {

		logger.info("web-service byNumber() invoked: " + accountNumber);

		Account account = accountsService.findByNumber(accountNumber);
		return account;
	}
}
