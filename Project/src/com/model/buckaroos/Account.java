package com.model.buckaroos;

import java.util.ArrayList;

import com.controller.buckaroos.UserAccountController;
import com.password.buckaroos.CredentialConfirmer;
/**
 * The "bank" accounts for user. 
 * @author Gene
 *
 */
public class Account {

	private String name;
	private int balance = 0;
	private double interestRate;
	private DB db;
	private ArrayList<Transaction> transactions;
	private UserAccountController controller;
	
	public Account(String name, int amount, double interestRate) {
		controller = new UserAccountController();
		transactions = new ArrayList<Transaction>();
		db = controller.getDB();
		this.setName(name);
		this.newDeposit(amount);
		this.setInterestRate(interestRate);
	}
	
	/**
	 * Returns balance of this account
	 * @return
	 */
	public int getBalance() {
		return balance;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}

	/**
	 * Creates new transaction for this account
	 * @param amount is the money to withdraw/deposit
	 */
	public void newWithdraw(int amount) {
		db.addWithdraw(this, amount);
	}
	
	public void newDeposit(int amount) {
		db.addDeposit(this, amount);
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
	
}
