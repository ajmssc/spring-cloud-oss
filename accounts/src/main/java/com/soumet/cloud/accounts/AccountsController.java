package com.soumet.cloud.accounts;

import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

/**
 * A RESTFul controller for accessing account information.
 * 
 * @author Paul Chapman
 */
@RestController
@RequestMapping("/accounts")
public class AccountsController {

	protected Logger logger = Logger.getLogger(AccountsController.class
			.getName());


	@ResponseBody
	@RequestMapping(method = RequestMethod.GET)
	public String get() {
		return "OK";
	}
	/**
	 * Fetch an account with the specified account number.
	 * 
	 * @param accountNumber
	 *            A numeric, 9 digit account number.
	 * @return The account if found.
	 * @throws AccountNotFoundException
	 *             If the number is not recognised.
	 */
	@RequestMapping("/{accountNumber}")
	public Account byNumber(@PathVariable("accountNumber") String accountNumber) {

		logger.info("accounts-service byNumber() invoked: " + accountNumber);
		Account account = new Account(accountNumber, "Jean-Marc");
//		if (account == null)
//			throw new AccountNotFoundException(accountNumber);
//		else {
			return account;
//		}
	}
}
