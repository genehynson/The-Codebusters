package com.controller.buckaroos;

import java.util.Date;
import java.util.List;

import com.model.buckaroos.Account;
import com.model.buckaroos.AccountTransaction;
import com.model.buckaroos.DB;
import com.model.buckaroos.User;

public interface ControllerInterface {

	/**
	 * Gets "bank" account
	 * 
	 * @param accountName
	 * @return
	 */
	public abstract Account getUserAccount(String accountName);

	/**
	 * adds "bank" account
	 * 
	 * @param accountName
	 */
	public abstract void addAccount(String accountName, String accountNickName);

	/**
	 *  adds "bank" account
	 * @param accountName
	 * @param accountNickName
	 * @param amount
	 */
	public abstract void addAccount(String accountName, String accountNickName,
			double amount);

	/**
	 *  adds "bank" account
	 * @param interestRate
	 * @param accountName
	 * @param accountNickName
	 */
	public abstract void addAccount(double interestRate, String accountName,
			String accountNickName);

	/**
	 *  adds "bank" account
	 * @param accountName
	 * @param accountNickName
	 * @param amount
	 * @param interestRate
	 */
	public abstract void addAccount(String accountName, String accountNickName,
			double amount, double interestRate);

	/**
	 * Add transactions to the current account
	 * 
	 * @param amount
	 * @param minute
	 * @param hour
	 */
	public abstract void addWithdrawal(double amount, String currencyType,
			String category, Date date);

	public abstract void addDeposit(double amount, String currencyType,
			String category, Date date);

	public abstract Account getCurrentAccount();

	/**
	 * does user have any "bank" accounts?
	 * 
	 * @return
	 */
	public abstract boolean hasAccount();

	/**
	 * gets the first (default) "bank" account
	 * 
	 * @return
	 */
	public abstract Account getFirstUserAccount();

	/**
	 * Sets current "bank" account
	 * @param account
	 */
	public abstract void setCurrentAccount(Account account);

	/**
	 * Returns the database
	 * @return
	 */
	public abstract DB getDB();

	/**
	 * Adds login account and sets static user
	 * 
	 * @param name
	 * @param password
	 * @param email
	 */
	public abstract void addLoginAccount(String name, String password,
			String email);

	/**
	 * Returns user's login account
	 * @param username
	 */
	public abstract User getLoginAccount(String username);

	/**
	 * Gets current user
	 * @return
	 */
	public abstract User getCurrentUser();

	/**
	 * Returns a list of all user "bank" accounts
	 * @return
	 */
	public abstract List<Account> getAllUserAccounts();

	/**
	 * Returns a list of all current user transactions
	 * @return
	 */
	public abstract List<AccountTransaction> getAllAccountTransactions();

	/**
	 * Checks if account exists
	 * @param accountName
	 * @return
	 */
	public abstract boolean doesLoginAccountExist(String accountName);

}