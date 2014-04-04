package com.buckaroos.client;

import java.util.List;
import java.util.Map;

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

    void addTransaction(String username, String accountName, double amount,
            String transactionType, String currencyType, String category,
            String transactionDate, String transactionTime);

    void updateAccountBalance(String username, String accountName, double amount);

    Map<String, Double> getSpendingCategoryInfo(String username,
            String accountName, String startDate, String endDate);

    Map<String, Double> getIncomeSourceInfo(String username,
            String accountName, String startDate, String endDate);

    Map<String, Double> getCashFlowReportInfo(String username,
            String startDate, String endDate);

    Map<String, Double> getAccountListingReportInfo(String username);

    Map<String, List<AccountTransaction>> getTransactionHistoryInfo(
            String username, String accountName, String startDate,
            String endDate);

    List<AccountTransaction> getCurrentTransactions(String username,
            String accountName);

    AccountTransaction getTransaction(String username, String accountName,
            double amount, String category, String transactionDate,
            String transactionTime);

    List<AccountTransaction> getAllTransactions(String username,
            String accountName);

    void removeTransaction(String username, String accountName, double amount,
            String category, String transactionDate, String transactionTime);

}