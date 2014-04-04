package com.buckaroos.client;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.buckaroos.server.Account;
import com.buckaroos.server.AccountTransaction;
import com.buckaroos.server.User;
import com.google.gwt.user.client.rpc.RemoteService;

public interface DBConnection extends RemoteService {
	
	void addUser(User user);
	
	User getUser(String username);

	void updateUser(String username, String password, String email);
	
	void addAccount(String username, Account account);
	
	Account getAccount(String username, String accountName);
	
	List<Account> getAllAccounts(String username);
	
	void addTransaction(String username, String accountName, double amount, String transactionType, String currencyType, String category, String transactionDate, String transactionTime);
	
	void updateAccountBalance(String username, String accountName, double amount);
	
	HashMap<String, Double> getSpendingCategoryInfo(String username, String accountName, String startDate, String endDate);
	
	HashMap<String, Double> getIncomeSourceInfo(String username, String accountName, String startDate, String endDate);
	
	AccountTransaction getTransaction(String username, String accountName, double amount, String category, String transactionDate, String transactionTime);
	
	List<AccountTransaction> getAllTransactions(String username, String accountName);
	
	void removeTransaction(String username, String accountName, double amount, String category, String transactionDate, String transactionTime);
	
	
}
