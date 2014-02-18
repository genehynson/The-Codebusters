package com.controller.buckaroos;

import java.util.ArrayList;

import com.model.buckaroos.Account;
import com.model.buckaroos.DB;
import com.model.buckaroos.Transaction;
import com.model.buckaroos.User;
import com.password.buckaroos.CredentialConfirmer;

public class UserAccountController {

	
	private static User user;
	private DB db;
	
	public UserAccountController(User user) {
		this.user = user;
		this.db = CredentialConfirmer.db;
	}
	
	public Account getUserAccount(String accountName) {
		return db.getAccount(user, accountName);
	}
	
	public void addAccount(String accountName) {
		db.addAccount(new Account(accountName, 0, 0), user);
	}
	
	public void addAccount(String accountName, int amount) {
		db.addAccount(new Account(accountName, amount, 0), user);
	}
	
	public void addAccount(String accountName, double interestRate) {
		db.addAccount(new Account(accountName, 0, interestRate), user);

	}
	
	public void addAccount(String accountName, int amount, double interestRate) {
		db.addAccount(new Account(accountName, amount, interestRate), user);

	}
	
}
