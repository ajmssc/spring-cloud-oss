package com.soumet.cloud.accounts.controllers;

import com.soumet.cloud.accounts.dto.Account;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/")
public class AccountsController {

	protected Logger logger = Logger.getLogger(AccountsController.class.getName());


	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return "Some cool sh*t";
	}

	@RequestMapping("/{accountNumber}")
	public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		Account account = new Account(accountNumber, "Jean-Marc");
        return account;
	}
}
