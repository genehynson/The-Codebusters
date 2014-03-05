package com.controller.buckaroos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import android.content.Context;

import com.model.buckaroos.Account;
import com.model.buckaroos.AccountTransaction;
import com.model.buckaroos.DB;
import com.model.buckaroos.User;
import com.utility.buckaroos.AppPropertyWriter;

/**
 * Controller between activities and User/Account
 * 
 * @author Gene
 * @version 1.0
 * 
 */
public class UserAccountController implements ControllerInterface {

    private static User user;
    private static DB db;
    private static Account currentAccount;
    private Context ctx;

    /**
     * Gets user/DB after login from CredientialConfirmer in Login activity
     * 
     * @param user
     */
    @SuppressWarnings("static-access")
    public UserAccountController(User user, Context ctx) {
        db = new DB(ctx);
        this.ctx = ctx;
        this.user = user;
    }

    public UserAccountController(Context ctx) {
        this.ctx = ctx;
        db = new DB(ctx);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getUserAccount(java.lang.String)
	 */
    @Override
	public Account getUserAccount(String accountName) {
        return db.getAccount(accountName, user);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addAccount(java.lang.String, java.lang.String)
	 */
    @Override
	public void addAccount(String accountName, String accountNickName) {
        currentAccount = new Account(accountName, accountNickName, 0, 0, user);
        db.addAccount(currentAccount, user);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addAccount(java.lang.String, java.lang.String, double)
	 */
    @Override
	public void addAccount(String accountName, String accountNickName,
            double amount) {
        currentAccount =
                new Account(accountName, accountNickName, amount, 0, user);
        db.addAccount(currentAccount, user);

    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addAccount(double, java.lang.String, java.lang.String)
	 */
    @Override
	public void addAccount(double interestRate, String accountName,
            String accountNickName) {
        currentAccount =
                new Account(accountName, accountNickName, 0, interestRate, user);
        db.addAccount(currentAccount, user);

    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addAccount(java.lang.String, java.lang.String, double, double)
	 */
    @Override
	public void addAccount(String accountName, String accountNickName,
            double amount, double interestRate) {
            currentAccount =
                    new Account(accountName, accountNickName, amount,
                            interestRate, user);
            db.addAccount(currentAccount, user);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addWithdrawal(double, java.lang.String, java.lang.String, java.util.Date)
	 */
    @Override
	public void
            addWithdrawal(double amount, String currencyType, String category,
                   Date date) {
        String dateString = convertDateToString(date);
        String timeString = convertTimeToString(date);
        db.addTransaction(currentAccount, user.getAccountName(), amount,
                "Withdrawal", currencyType, category, dateString, timeString);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addDeposit(double, java.lang.String, java.lang.String, java.util.Date)
	 */
    @Override
	public void addDeposit(double amount, String currencyType, String category,
            Date date) {
        String dateString = convertDateToString(date);
        String timeString = convertTimeToString(date);
        db.addTransaction(currentAccount, user.getAccountName(), amount,
                "Deposit", currencyType, category, dateString, timeString);
    }

   
    /**
     * Converts integer time to strings
     * @param hour
     * @param minute
     * @return
     */
    private String convertTimeToString(Date date) {
    	DateFormat df = new SimpleDateFormat("HH:mm");
    	return df.format(date);
    }
    
    /**
     * Converts date into string
     * @param date
     * @return
     */
    private String convertDateToString(Date date) {
    	DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    	return df.format(date);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getCurrentAccount()
	 */
    @Override
	public Account getCurrentAccount() {
        return currentAccount;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#hasAccount()
	 */
    @Override
	public boolean hasAccount() {
        return db.getFirstAccount(user) != null;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getFirstUserAccount()
	 */
    @Override
	public Account getFirstUserAccount() {
        return db.getFirstAccount(user);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#setCurrentAccount(com.model.buckaroos.Account)
	 */
    @Override
	public void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getDB()
	 */
    @Override
	public DB getDB() {
        return db;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addLoginAccount(java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
	public void addLoginAccount(String name, String password, String email) {
        AppPropertyWriter k = new AppPropertyWriter(ctx);
        user = k.storeAccount(name, password, email);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getLoginAccount(java.lang.String)
	 */
    @Override
	public User getLoginAccount(String username) {
        return db.getUser(username);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getCurrentUser()
	 */
    @Override
	public User getCurrentUser() {
        return user;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getAllUserAccounts()
	 */
    @Override
	public List<Account> getAllUserAccounts() {
        return db.getAllAccounts(user);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getAllAccountTransactions()
	 */
    @Override
	public List<AccountTransaction> getAllAccountTransactions() {
        return db.getAllAccountTransactions(currentAccount, user);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#doesLoginAccountExist(java.lang.String)
	 */
    @Override
	public boolean doesLoginAccountExist(String accountName) {
        return db.getUser(accountName) != null;
    }
}
