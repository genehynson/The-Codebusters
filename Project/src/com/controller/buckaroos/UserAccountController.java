package com.controller.buckaroos;

import java.util.ArrayList;

import com.model.buckaroos.Account;
import com.model.buckaroos.DB;
import com.model.buckaroos.Transaction;
import com.model.buckaroos.User;
import com.password.buckaroos.CredentialConfirmer;
/**
 * Controller between activities and User/Account
 * @author Gene
 *
 */
public class UserAccountController {


	private static User user;
	private static DB db;
	private static Account currentAccount;
	/**
	 * Gets user/DB after login from CredientialConfirmer in Login activity
	 * @param user
	 */
	public UserAccountController(User user, DB db) {
		this.user = user;
		this.db = db;
	}
	/**
	 * User, DB are static...
	 */
	public UserAccountController() {
		//Need to make this instantiate with a user all the time in order for 
		//database to work properly
	}
	/**
	 * Gets "bank" account
	 * @param accountName
	 * @return
	 */
	public Account getUserAccount(String accountName) {
		return db.getAccount(accountName, user);
	}
	/**
	 * adds "bank" account
	 * @param accountName
	 */
	public void addAccount(String accountName) {
		currentAccount = new Account(accountName, 0, 0, user.get_accountName());
		db.addAccount(currentAccount, user);
	}

	public void addAccount(String accountName, double amount) {
		currentAccount = new Account(accountName, amount, 0, user.get_accountName());
		db.addAccount(currentAccount, user);

	}

	public void addAccount(double interestRate, String accountName) {
		currentAccount = new Account(accountName, 0, interestRate, user.get_accountName());
		db.addAccount(currentAccount, user);


	}

	public void addAccount(String accountName, double amount, double interestRate) {
		//Getting nullpointerexception here because this class can be 
		//instantiated without a user sometimes
//		if (user.get_accountName() != null) {
//			currentAccount = new Account(accountName, amount, interestRate, user.get_accountName());
//		} else {
		currentAccount = new Account(accountName, amount, interestRate, "jordan");
//		}
		db.addAccount(currentAccount, user);


	}
	/**
	 * Add transactions to the current account
	 * @param amount
	 */
	public void addWithdrawal(double amount, String currencyType, String category) {
		db.addTransaction(currentAccount, user.get_accountName(), amount, "Withdrawal",
				currencyType, category);
	}

	public void addDeposit(double amount, String currencyType, String category) {
		db.addTransaction(currentAccount, user.get_accountName(), amount, "Deposit",
				currencyType, category);
	}

	public Account getCurrentAccount() {
		return currentAccount;
	}


	/**
	 * does user have any "bank" accounts?
	 * @return
	 */
	public boolean hasAccount() {
		return !user.getAccounts().isEmpty();
	}
	/**
	 * gets the first (default) "bank" account
	 * @return
	 */
	public Account getFirstUserAccount() {
		return db.getAccount(currentAccount.getName(), user);
	}

	public DB getDB() {
		return db;
	}
}
