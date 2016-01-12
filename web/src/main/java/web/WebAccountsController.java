package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import web.accounts.Account;
import web.accounts.AccountsRemoteService;

import java.util.logging.Logger;


@Controller
@RequestMapping("/accounts-monitored")
public class WebAccountsController {

	@Autowired
	protected AccountsRemoteService accountsService;


    protected Logger logger = Logger.getLogger(WebAccountsController.class.getName());


	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.setAllowedFields("accountNumber", "searchText");
	}

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public String goHome() {
		return "WebAccountsController::home";
	}

	@RequestMapping("/{accountNumber}")
	@ResponseBody
	public Account byNumber(Model model, @PathVariable("accountNumber") String accountNumber) {
		logger.info("web-service byNumber() invoked: " + accountNumber);
		Account account = accountsService.findByNumber(accountNumber);
		return account;
	}
}
