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
		
	}
	/**
	 * Gets "bank" account
	 * @param accountName
	 * @return
	 */
	public Account getUserAccount(String accountName) {
		return db.getAccount(user, accountName);
	}
	/**
	 * adds "bank" account
	 * @param accountName
	 */
	public void addAccount(String accountName) {
		currentAccount = new Account(accountName, 0, 0);
		db.addAccount(currentAccount, user);
	}
	
	public void addAccount(String accountName, int amount) {
		currentAccount = new Account(accountName, amount, 0);
		db.addAccount(currentAccount, user);

	}
	
	public void addAccount(String accountName, double interestRate) {
		currentAccount = new Account(accountName, 0, interestRate);
		db.addAccount(currentAccount, user);


	}
	
	public void addAccount(String accountName, int amount, double interestRate) {
		currentAccount = new Account(accountName, amount, interestRate);
		db.addAccount(currentAccount, user);

		
	}
	/**
	 * Add transactions to the current account
	 * @param amount
	 */
	public void addWithdraw(int amount) {
		db.addWithdraw(currentAccount, amount);
	}
	
	public void addDeposit(int amount) {
		db.addDeposit(currentAccount, amount);
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
		return db.getAccount(user);
	}
	
	public DB getDB() {
		return db;
	}
}
