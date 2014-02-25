package com.controller.buckaroos;

import java.util.ArrayList;

import android.content.Context;

import com.model.buckaroos.Account;
import com.model.buckaroos.DB;
import com.model.buckaroos.Transaction;
import com.model.buckaroos.User;
import com.password.buckaroos.AppPropertyWriter;
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
	private Context ctx;
	/**
	 * Gets user/DB after login from CredientialConfirmer in Login activity
	 * 
	 * @param user
	 */
	public UserAccountController(User user, DB db) {
		this.user = user;
		this.db = db;
	}
	
	/**
	 * User uses this constructor
	 * @param user
	 */
	public UserAccountController(User user) {
		this.user = user;
	}
	
	/**
	 * UI classes use this constructor
	 */
	public UserAccountController(Context ctx) {
		this.ctx = ctx;
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
		currentAccount = new Account(accountName, 0, 0, user.get_accountName(), user);
		db.addAccount(currentAccount, user);
	}

	public void addAccount(String accountName, double amount) {
		currentAccount = new Account(accountName, amount, 0, user.get_accountName(), user);
		db.addAccount(currentAccount, user);

	}

	public void addAccount(double interestRate, String accountName) {
		currentAccount = new Account(accountName, 0, interestRate, user.get_accountName(), user);
		db.addAccount(currentAccount, user);


	}

	public void addAccount(String accountName, double amount, double interestRate) {
		//Getting nullpointerexception here because this class can be 
		//instantiated without a user sometimes
		if (user != null) {
			currentAccount = new Account(accountName, amount, interestRate, user.get_accountName(), user);
			db.addAccount(currentAccount, user);
		} else {
			System.out.println("User is null.");
		}


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
	
	public void setCurrentAccount(Account account) {
		currentAccount = account;
	}

	public DB getDB() {
		return db;
	}
	
	/**
	 * Adds login account and sets static user
	 * @param name
	 * @param password
	 * @param email
	 */
	public void addLoginAccount(String name, String password, String email) {
		AppPropertyWriter k = new AppPropertyWriter(ctx);
		user = k.storeAccount(name, password, email);
	}
	
	public User getLoginAccount(String username) {
		return db.getUser(username);
	}
	
}
