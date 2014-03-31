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
 * This class defines an Interface for the controller object.
 * 
 * @author Gene Hynson
 * @author Daniel Carnauba
 * @version 1.0
 */
public interface ControllerInterface {

    /**
     * Gets the user "bank" account.
     * 
     * @param accountName The user's account name.
     * @return The user's account. ("THE BANK ACCOUNT")
     */
    public abstract Account getUserAccount(String accountName);

    /**
     * Adds a "bank" account for the logged in user.
     * 
     * @param accountName The official account name (i.e savings)
     * @param accountNickName The account nickname for display (i.e cashstash)
     * @param amount The initial amount for the account being created.
     * @param interestRate The interest rate for this account created.
     */
    public abstract void addAccount(String accountName, String accountNickName,
            double amount, double interestRate);

    /**
     * Adds a withdrawal transaction to the current active account.
     * 
     * @param amount The withdrawal amount.
     * @param currencyType The currency type for the transaction.
     * @param category The category of the transaction. (Food, bills, etc.)
     * @param date The date when the transaction takes place.
     */
    public abstract void addWithdrawal(double amount, String currencyType,
            String category, Date date);

    /**
     * Adds a deposit transaction to the current active account.
     * 
     * @param amount The deposit amount for this transaction.
     * @param currencyType The currency type for this transaction.
     * @param category The source of income for this transaction (i.e Paycheck)
     * @param date The date when the transaction takes place.
     */
    public abstract void addDeposit(double amount, String currencyType,
            String category, Date date);

    /**
     * Gets the current active account.
     * 
     * @return The current account that is currently active.
     */
    public abstract Account getCurrentAccount();

    /**
     * Checks if the user has an account.(i.e savings, checking, etc.)
     * 
     * @return True if such account exists, False otherwise.
     */
    public abstract boolean hasAccount();

    /**
     * Gets the first (default) "bank" account.
     * 
     * @return The default user "bank" account.
     */
    public abstract Account getFirstUserAccount();

    /**
     * Sets the current "bank" account.
     * 
     * @param account The account to be set.
     */
    public abstract void setCurrentAccount(Account account);

    /**
     * Gets the database.
     * 
     * @return The database object.
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
     * Gets user's login account.
     * 
     * @param username The user's user name.
     */
    public abstract User getLoginAccount(String username);

    /**
     * Gets the current user.
     * 
     * @return The current user object.
     */
    public abstract User getCurrentUser();

    /**
     * Gets a list of all user "bank" accounts.
     * 
     * @return A list of all existing accounts for one user.
     */
    public abstract List<Account> getAllUserAccounts();

    /**
     * Gets a list of all current user transactions.
     * 
     * @return A list of all transactions within a user's account.
     */
    public abstract List<AccountTransaction> getAllAccountTransactions();

    /**
     * Checks if the login account exists.
     * 
     * @param accountName The name of the login account.
     * @return True if account exists, False otherwise.
     */
    public abstract boolean doesLoginAccountExist(String accountName);

    /**
     * Generates a Spending category report across all user accounts. Summarizes
     * withdrawal transactions by category for a specified time period. Sum up
     * all the withdrawal transactions for each category and then display.
     * 
     * @return The spending category report across all user accounts.
     */
    public abstract Map<String, Double> generateSpendingCategoryReport();

    /**
     * Generates a source of income report. Summarizes deposit transactions by
     * source for a specified time period. Sum up all the deposit transactions
     * for each source and then display.
     * 
     * @return The source of income report across all user accounts.
     */
    public abstract Map<String, Double> generateIncomeSourceReport();

    /**
     * Generates a cash flow report Compares withdrawals to deposits for a given
     * period of time. Simply sum up all the withdrawals and subtract them from
     * all the deposits.
     * 
     * @return The cash flow report.
     */
    public abstract Map<String, Double> generateCashFlowReport();

    /**
     * Generates an account listing report. Shows all the accounts for the user
     * with their current balance. Should have to option to go to the
     * transaction history for that account and rollback or commit a previously
     * rollbacked transaction.
     * 
     * @return
     */
    public abstract Map<String, Double> generateAccountListingReport();

    /**
     * Generates a transaction history report for an specific account. For any
     * given account, you should be able to view all the transactions over a
     * given time period that have affected the balance of that account.
     * 
     * @return
     */
    public abstract Map<String, Double> generateTransactionHistoryReport();

    /**
     * @return The transaction category names.
     */
    public abstract List<String> getTransactionNamesInDate();

    /**
     * @return The transaction category names.
     */
    public abstract Map<String, Double> getTransactionsInDate();

    //
    // /**
    // * @return The income transactions totals.
    // */
    // public abstract Map<String, Double> getIncomeTransactionsTotalInDate();
    //
    // /**
    // * @return The transaction income source.
    // */
    // public abstract List<String> getIncomeTransactionSourceInDate();
    //
    // /**
    // * @return
    // */
    // public abstract Map<String, Double> getTransactionHistoryTotalInDate();
    //
    // /**
    // * @return
    // */
    // public abstract List<String> getTransactionHistoryNamesInDate();

    /**
     * Confirms the login credentials for an user.
     * 
     * @param username The user's user name.
     * @param password The user's password.
     * @param confirm The CredentialCorfimer object.
     * @return True if the credentials are confirmed, False otherwise.
     */
    public abstract boolean confirmLogin(String username, String password,
            CredentialConfirmer confirm);
}