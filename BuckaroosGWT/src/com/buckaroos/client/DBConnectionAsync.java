package com.buckaroos.client;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.buckaroos.server.Account;
import com.buckaroos.server.AccountTransaction;
import com.buckaroos.server.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface DBConnectionAsync {

	void addUser(User user, AsyncCallback callback);
	
	void getUser(String username, AsyncCallback<User> callback);

	void updateUser(String username, String password, String email, AsyncCallback callback);
	
	void addAccount(String username, Account account, AsyncCallback callback);
	
	void getAccount(String username, String accountName, AsyncCallback<Account> callback);
	
	void getAllAccounts(String username, AsyncCallback<List<Account>> callback);
	
	void addTransaction(String username, String accountName, double amount, String transactionType, String currencyType, String category, String transactionDate, String transactionTime, AsyncCallback callback);
	
	void updateAccountBalance(String username, String accountName, double amount, AsyncCallback callback);
	
	void getSpendingCategoryInfo(String username, String accountName, String startDate, String endDate, AsyncCallback<HashMap<String, Double>> callback);
	
	void getIncomeSourceInfo(String username, String accountName, String startDate, String endDate, AsyncCallback<HashMap<String, Double>> callback);
	
	void getTransaction(String username, String accountName, double amount, String category, String transactionDate, String transactionTime, AsyncCallback<AccountTransaction> callback);
	
	void getAllTransactions(String username, String accountName, AsyncCallback<List<AccountTransaction>> callback);
	
	void removeTransaction(String username, String accountName, double amount, String category, String transactionDate, String transactionTime, AsyncCallback callback);
	
	
}
