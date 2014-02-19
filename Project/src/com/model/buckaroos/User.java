package com.model.buckaroos;

import java.util.ArrayList;

import com.controller.buckaroos.UserAccountController;

/**
 * Class designed to store the user's credentials
 * 
 * @author Jordan
 * @version 1.0
 */
public class User {
	
	private String _accountName;
	private String _password;
	private String _email;
	private ArrayList<Account> accounts;
	private UserAccountController controller;
	private DB db;
	
	public User(String accountName, String password, String email) {
		controller = new UserAccountController();
		db = controller.getDB();
		this._accountName = accountName;
//		PasswordHash hasher = new PasswordHash();
//		this._password = hasher.hashPassword(password);
		this._password = password;
		this._email = email;
		accounts = new ArrayList<Account>();
	}

	public String get_accountName() {
		return _accountName;
	}

	public void set_accountName(String _accountName) {
		this._accountName = _accountName;
	}

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
//		PasswordHash hasher = new PasswordHash();
//		this._password = hasher.hashPassword(_password);
		this._password = _password;
	}

	public String get_email() {
		return _email;
	}

	public void set_email(String _email) {
		this._email = _email;
	}
	
	public ArrayList<Account> getAccounts() {
		return accounts;
	}
	
	public void addAccount(Account account) {
		db.addAccount(account, this);
	}
	
	public boolean makeDeposit(String accountName, int amount) {
		db.addDeposit(db.getAccount(this, accountName), amount);
		return true;
	}
	
	public boolean makeWithdraw(String accountName, int amount) {
		db.addWithdraw(db.getAccount(this, accountName), amount);
		return true;
	}
	
	public boolean equals(Object o) {
		if (o == null) return false;
		if (o == this) return true;
		if (o instanceof Account){
			if (((User) o).get_accountName().equals(this.get_accountName()) && ((User) o).get_email() == this.get_email()) {
				return true;
			}
		}
		return false;
	}
	
}