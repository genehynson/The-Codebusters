package com.password.buckaroos;

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
	
	public User(String accountName, String password, String email) {
		this._accountName = accountName;
//		PasswordHash hasher = new PasswordHash();
//		this._password = hasher.hashPassword(password);
		this._password = password;
		this._email = email;
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
	
	
	
}