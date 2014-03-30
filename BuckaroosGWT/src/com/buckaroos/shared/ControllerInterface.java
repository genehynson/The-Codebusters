package com.buckaroos.shared;

import java.util.Date;
import java.util.List;
import java.util.Map;


public interface ControllerInterface {

	/**
	 * Gets "bank" account
	 * 
	 * @param accountName
	 * @return
	 */
	public abstract com.buckaroos.server.Account getUserAccount(String accountName);

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

	public abstract com.buckaroos.server.Account getCurrentAccount();

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
	public abstract com.buckaroos.server.Account getFirstUserAccount();

	/**
	 * Sets current "bank" account
	 * @param account
	 */
	public abstract void setCurrentAccount(com.buckaroos.server.Account account);

	/**
	 * Returns the database
	 * @return
	 */
//	public abstract com.buckaroos.server.DB getDB();

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
	public abstract com.buckaroos.server.User getLoginAccount(String username);

	/**
	 * Gets current user
	 * @return
	 */
	public abstract com.buckaroos.server.User getCurrentUser();

	/**
	 * Returns a list of all user "bank" accounts
	 * @return
	 */
	public abstract List<com.buckaroos.server.Account> getAllUserAccounts();

	/**
	 * Returns a list of all current user transactions
	 * @return
	 */
	public abstract List<com.buckaroos.server.AccountTransaction> getAllAccountTransactions();

	/**
	 * Checks if account exists
	 * @param accountName
	 * @return
	 */
	public abstract boolean doesLoginAccountExist(String accountName);

	public abstract Map<String, Double> getTransactionsInDate();

	public abstract List<String> getTransactionNamesInDate();

	public abstract boolean confirmLogin(String string, String string2,
			com.buckaroos.utility.CredentialConfirmer confirm);
	public abstract List<String> getAllObjectNames();
		
	public abstract Object getObject(String name);

	public abstract void addObject(String name, Object o);
	
}