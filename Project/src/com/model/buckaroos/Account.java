package com.model.buckaroos;

import java.util.ArrayList;

import com.controller.buckaroos.UserAccountController;
/**
 * The "bank" accounts for user. 
 * @author Gene
 *
 */
public class Account {

	private String name;
	private double balance = 0;
	private double interestRate;
	private DB db;
	private ArrayList<Transaction> transactions;
	private UserAccountController controller;
	private String userAccountName;
	
	public Account(String name, double amount, double interestRate, String userAccountName) {
		controller = new UserAccountController();
		transactions = new ArrayList<Transaction>();
		db = controller.getDB();
		this.setName(name);
		this.balance = amount;
		this.setInterestRate(interestRate);
		this.userAccountName = userAccountName;
	}
	
	/**
	 * Returns balance of this account
	 * @return
	 */
	public double getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Creates new transaction for this account
	 * @param amount is the money to withdraw/deposit
	 */
	public void newWithdrawal(double amount, String currencyType, String category) {
		db.addTransaction(this, userAccountName, amount, "Withdrawal", 
				currencyType, category);
		
	}
	
	public void newDeposit(double amount, String currencyType, String category) {
		db.addTransaction(this, userAccountName, amount, "Deposit", 
				currencyType, category);
	}

	/**
	 * get name of account
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * set name of account
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (o instanceof Account){
			if (((Account) o).getName().equals(this.getName()) && ((Account) o).getBalance() == this.getBalance()) {
				return true;
			}
		}
		return false;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	public ArrayList<Transaction> getAllTransactions() {
		return transactions;
	}

	public String getUserAccountName() {
		return userAccountName;
	}
	
}
