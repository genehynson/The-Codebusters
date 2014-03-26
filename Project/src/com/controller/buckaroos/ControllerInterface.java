package com.controller.buckaroos;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.model.buckaroos.Account;
import com.model.buckaroos.AccountTransaction;
import com.model.buckaroos.DB;
import com.model.buckaroos.User;
import com.utility.buckaroos.CredentialConfirmer;

/**
 * @author Gene Hynson
 * @author Daniel Carnauba
 */
public interface ControllerInterface {

    /**
     * Gets "bank" account
     * 
     * @param accountName
     * @return
     */
    public abstract Account getUserAccount(String accountName);

    /**
     * adds "bank" account
     * 
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

    /**
     * @param amount
     * @param currencyType
     * @param category
     * @param date
     */
    public abstract void addDeposit(double amount, String currencyType,
            String category, Date date);

    /**
     * @return
     */
    public abstract Account getCurrentAccount();

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
    public abstract Account getFirstUserAccount();

    /**
     * Sets current "bank" account
     * 
     * @param account
     */
    public abstract void setCurrentAccount(Account account);

    /**
     * Returns the database
     * 
     * @return
     */
    public abstract DB getDB();

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
     * 
     * @param username
     */
    public abstract User getLoginAccount(String username);

    /**
     * Gets current user
     * 
     * @return
     */
    public abstract User getCurrentUser();

    /**
     * Returns a list of all user "bank" accounts
     * 
     * @return
     */
    public abstract List<Account> getAllUserAccounts();

    /**
     * Returns a list of all current user transactions
     * 
     * @return
     */
    public abstract List<AccountTransaction> getAllAccountTransactions();

    /**
     * Checks if account exists
     * 
     * @param accountName
     * @return
     */
    public abstract boolean doesLoginAccountExist(String accountName);

    /**
     * Gets the t
     * 
     * @return The transaction category totals.
     */
    public abstract Map<String, Double> getTransactionsInDate();

    /**
     * @return The transaction category names.
     */
    public abstract List<String> getTransactionNamesInDate();

    /**
     * @return The income transactions totals.
     */
    public abstract Map<String, Double> getIncomeTransactionsTotalInDate();

    /**
     * @return The transaction income source.
     */
    public abstract List<String> getIncomeTransactionSourceInDate();

    /**
     * @return
     */
    public abstract Map<String, Double> getTransactionHistoryTotalInDate();

    /**
     * @return
     */
    public abstract List<String> getTransactionHistoryNamesInDate();

    /**
     * @param string
     * @param string2
     * @param confirm
     * @return
     */
    public abstract boolean confirmLogin(String string, String string2,
            CredentialConfirmer confirm);
}