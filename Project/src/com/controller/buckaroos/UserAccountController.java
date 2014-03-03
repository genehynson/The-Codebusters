package com.controller.buckaroos;

import java.util.ArrayList;

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
public class UserAccountController {

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

    /**
     * Gets "bank" account
     * 
     * @param accountName
     * @return
     */
    public Account getUserAccount(String accountName) {
        return db.getAccount(accountName, user);
    }

    /**
     * adds "bank" account
     * 
     * @param accountName
     */
    public void addAccount(String accountName) {
        currentAccount = new Account(accountName, 0, 0, user);
        db.addAccount(currentAccount, user);
    }

    public void addAccount(String accountName, double amount) {
        currentAccount = new Account(accountName, amount, 0, user);
        db.addAccount(currentAccount, user);

    }

    public void addAccount(double interestRate, String accountName) {
        currentAccount = new Account(accountName, 0, interestRate, user);
        db.addAccount(currentAccount, user);

    }

    public void addAccount(String accountName, double amount,
            double interestRate) {
        if (user != null) {
            currentAccount = new Account(accountName, amount, interestRate,
                    user);
            db.addAccount(currentAccount, user);
        } else {
            System.out.println("User is null.");
        }

    }

    /**
     * Add transactions to the current account
     * 
     * @param amount
     * @param minute
     * @param hour
     */
    public void addWithdrawal(double amount, String currencyType,
            String category, int hour, int minute, int day, int month, int year) {
        String date;
        String time;
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);
        String hourStr = String.valueOf(hour);
        String minuteStr = String.valueOf(minute);
        if (month < 10) {
            monthStr = "0" + String.valueOf(month);
        }
        if (day < 10) {
            dayStr = "0" + String.valueOf(day);
        }
        date = monthStr + "/" + dayStr + "/" + String.valueOf(year);
        if (hour < 10) {
            hourStr = "0" + hour;
        }
        if (minute < 10) {
            minuteStr = "0" + minute;
        }
        time = hourStr + ":" + minuteStr;
        db.addTransaction(currentAccount, user.getAccountName(), amount,
                "Withdrawal", currencyType, category, date, time);
    }

    public void addDeposit(double amount, String currencyType, String category,
            int hour, int minute, int day, int month, int year) {
        String date;
        String time;
        String monthStr = String.valueOf(month);
        String dayStr = String.valueOf(day);
        String hourStr = String.valueOf(hour);
        String minuteStr = String.valueOf(minute);
        if (month < 10) {
            monthStr = "0" + String.valueOf(month);
        }
        if (day < 10) {
            dayStr = "0" + String.valueOf(day);
        }
        date = monthStr + "/" + dayStr + "/" + String.valueOf(year);
        if (hour < 10) {
            hourStr = "0" + hour;
        }
        if (minute < 10) {
            minuteStr = "0" + minute;
        }
        time = hourStr + ":" + minuteStr;
        db.addTransaction(currentAccount, user.getAccountName(), amount,
                "Deposit", currencyType, category, date, time);
    }

    public Account getCurrentAccount() {
        return currentAccount;
    }

    /**
     * does user have any "bank" accounts?
     * 
     * @return
     */
    public boolean hasAccount() {
        return db.getFirstAccount(user) != null;
    }

    /**
     * gets the first (default) "bank" account
     * 
     * @return
     */
    public Account getFirstUserAccount() {
        return db.getFirstAccount(user);
    }

    public void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    public DB getDB() {
        return db;
    }

    /**
     * Adds login account and sets static user
     * 
     * @param name
     * @param password
     * @param email
     */
    public void addLoginAccount(String name, String password, String email) {
        AppPropertyWriter k = new AppPropertyWriter(ctx);
        user = k.storeAccount(name, password, email);
    }

    public User getLoginAccount(String username) {
        return db.getUser(username);
    }

    public User getCurrentUser() {
        return user;
    }

    public ArrayList<Account> getAllUserAccounts() {
        return db.getAllAccounts(user);
    }

    public ArrayList<AccountTransaction> getAllAccountTransactions() {
        return db.getAllAccountTransactions(currentAccount, user);
    }

    public boolean doesLoginAccountExist(String accountName) {
        return db.getUser(accountName) != null;
    }
}
