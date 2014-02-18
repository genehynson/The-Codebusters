package com.controller.buckaroos;

import com.model.buckaroos.Account;
import com.model.buckaroos.DB;
import com.model.buckaroos.User;
import com.password.buckaroos.CredentialConfirmer;

public class UserAccountController {

	
	private User user;
	private DB db;
	
	public UserAccountController(User user) {
		this.user = user;
		this.db = CredentialConfirmer.db;
		
	}
	
	public Account getUserAccount(String accountName) {
		return db.getAccount(user, accountName);
	}
	
	public void addAccount(String accountName) {
		db.addAccount(new Account(accountName), user);
	}
	
	public void addAccount(String accountName, int amount) {
		db.addAccount(new Account(accountName, amount), user);
	}
	
}
