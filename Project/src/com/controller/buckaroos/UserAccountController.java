package com.controller.buckaroos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.content.Context;

import com.model.buckaroos.Account;
import com.model.buckaroos.AccountTransaction;
import com.model.buckaroos.DB;
import com.model.buckaroos.User;
import com.utility.buckaroos.AppPropertyWriter;
import com.utility.buckaroos.CredentialConfirmer;

/**
 * This class implements the ControllerInterface interface. This controller
 * object acts as a controller in the Model-View-Controller model. Controls the
 * communication between activities and User/Account.
 *
 * @author Gene Hynson
 * @author Daniel Carnauba
 * @version 1.0
 */
public class UserAccountController implements ControllerInterface {

    private User user;
    private DB db;
    private Account currentAccount;
    private final Context ctx;
    private static Date beginDate;
    private static Date theDate;
    private static Date endDate;

    /**
     * Gets user/DB after login from CredientialConfirmer in Login activity.
     *
     * @param user The user.
     * @param ctx The context.
     */
    public UserAccountController(User user, Context ctx) {
        db = new DB(ctx);
        this.ctx = ctx;
        this.user = user;
    }

    /**
     * Constructs an user account controller object.
     *
     * @param ctx The context.
     */
    public UserAccountController(Context ctx) {
        this.ctx = ctx;
        db = new DB(ctx);
    }

    @Override
    public Account getUserAccount(String accountName) {
        return db.getAccount(accountName, user);
    }

    @Override
    public void addAccount(String accountName, String accountNickName,
            double amount, double interestRate) {
        currentAccount =
                new Account(accountName, accountNickName, amount,
                        interestRate, user);
        db.addAccount(currentAccount, user);
    }

    @Override
    public void addWithdrawal(double amount, String currencyType,
            String category, Date date) {
        String dateString = convertDateToString(date);
        String timeString = convertTimeToString(date);
        db.addTransaction(currentAccount, user.getAccountName(), amount,
                "Withdrawal", currencyType, category, dateString,
                timeString);
    }

    @Override
    public void addDeposit(double amount, String currencyType,
            String category, Date date) {
        String dateString = convertDateToString(date);
        String timeString = convertTimeToString(date);
        db.addTransaction(currentAccount, user.getAccountName(), amount,
                "Deposit", currencyType, category, dateString, timeString);
    }

    /**
     * Converts an integer time to a string representation.
     *
     * @param date The date from which the time is being derived from.
     * @return The formatted time string representation.
     */
    public static String convertTimeToString(Date date) {
        String formattedTime = null;
        if (date != null) {
            DateFormat df = new SimpleDateFormat("hh:mm:ss", Locale.US);
            formattedTime = df.format(date);
        }
        return formattedTime;
    }

    /**
     * Converts a Date object into a string.
     *
     * @param date The date object to be converted to a string.
     * @return The string conversion of the date object.
     */
    public static String convertDateToString(Date date) {
        String formattedDate = null;
        if (date != null) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
            formattedDate = df.format(date);
        }
        return formattedDate;
    }

    /**
     * Converts a string to a Date object.
     *
     * @param dateString The date string passed in for conversion.
     * @return The date object converted from the string.
     */
    public static Date convertStringToDate(String dateString) {
        Date theDate = null;
        if (dateString != null) {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd", Locale.US);
            try {
                theDate = df.parse(dateString);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return theDate;
    }

    @Override
    public Account getCurrentAccount() {
        return currentAccount;
    }

    @Override
    public boolean hasAccount() {
        return db.getFirstAccount(user) != null;
    }

    @Override
    public Account getFirstUserAccount() {
        return db.getFirstAccount(user);
    }

    @Override
    public void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    @Override
    public DB getDB() {
        return db;
    }

    @Override
    public void
            addLoginAccount(String name, String password, String email) {
        AppPropertyWriter k = new AppPropertyWriter(ctx);
        user = k.storeAccount(name, password, email);
    }

    @Override
    public User getLoginAccount(String username) {
        return db.getUser(username);
    }

    @Override
    public User getCurrentUser() {
        return user;
    }

    @Override
    public List<Account> getAllUserAccounts() {
        return db.getAllAccounts(user);
    }

    @Override
    public List<AccountTransaction> getAllAccountTransactions() {
        return db.getAllAccountTransactions(currentAccount, user);
    }

    @Override
    public boolean doesLoginAccountExist(String accountName) {
        return db.getUser(accountName) != null;
    }

    @Override
    public Map<String, Double> getTransactionsInDate() {
        List<AccountTransaction> importedTransactions =
                getAllAccountTransactions();
        List<Account> allAccounts = getAllUserAccounts();
        Account actualCurrentAccount = getCurrentAccount();

        List<String> categoryNames = new ArrayList<String>();
        Map<String, Double> categoryTotals = new HashMap<String, Double>();

        String date;
        double totalSpending = 0;

        for (Account account : allAccounts) {
            setCurrentAccount(account);
            importedTransactions = getAllAccountTransactions();
            for (AccountTransaction transaction : importedTransactions) {
                date = transaction.getDate();
                if (!date.equals("00/00/0")) {
                    theDate = convertStringToDate(date);
                    System.out.println("before " + beginDate);
                    System.out.println(theDate.toString());
                    System.out.println("after " + endDate);
                    if ((beginDate.before(theDate) && endDate
                            .after(theDate))
                            || beginDate.equals(theDate)
                            || endDate.equals(theDate)) {
                        System.out.println("found date");
                        if (transaction.getType().equals("Withdrawal")) {
                            if (!categoryTotals.containsKey(transaction
                                    .getCategory())) {
                                categoryTotals.put(
                                        transaction.getCategory(),
                                        transaction.getAmount());
                                categoryNames.add(transaction
                                        .getCategory());
                            } else {
                                categoryTotals.put(
                                        transaction.getCategory(),
                                        categoryTotals.get(transaction
                                                .getCategory())
                                                + transaction.getAmount());
                            }
                            totalSpending =
                                    transaction.getAmount()
                                            + totalSpending;
                        }
                    }
                }
            }
        }
        categoryTotals.put("Total", totalSpending);
        categoryNames.add("Total");
        setCurrentAccount(actualCurrentAccount);
        return categoryTotals;
    }

    @Override
    public List<String> getTransactionNamesInDate() {
        List<String> categoryNames = new ArrayList<String>();

        List<AccountTransaction> importedTransactions =
                getAllAccountTransactions();
        List<Account> allAccounts = getAllUserAccounts();
        Account actualCurrentAccount = getCurrentAccount();

        String date;

        for (Account account : allAccounts) {
            setCurrentAccount(account);
            importedTransactions = getAllAccountTransactions();
            for (AccountTransaction transaction : importedTransactions) {
                date = transaction.getDate();
                if (!date.equals("00/00/0")) {
                    theDate = convertStringToDate(date);
                    if ((beginDate.before(theDate) && endDate
                            .after(theDate))
                            || beginDate.equals(theDate)
                            || endDate.equals(theDate)) {
                        if (transaction.getType().equals("Withdrawal")) {
                            if (!categoryNames.contains(transaction
                                    .getCategory())) {
                                categoryNames.add(transaction
                                        .getCategory());
                            }
                        }
                    }
                }
            }
        }
        categoryNames.add("Total");
        setCurrentAccount(actualCurrentAccount);
        return categoryNames;
    }

    @Override
    public Map<String, Double> generateSpendingCategoryReport() {
        return db.getSpendingCategoryInfo(user.getAccountName(),
                currentAccount.getName(), beginDate.toString(),
                endDate.toString());
    }

    @Override
    public Map<String, Double> generateIncomeSourceReport() {
        return db.getIncomeSourceInfo(user.getAccountName(),
                currentAccount.getName(), beginDate.toString(),
                endDate.toString());
    }

    @Override
    public Map<String, Double> generateCashFlowReport() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Double> generateAccountListingReport() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Map<String, Double> generateTransactionHistoryReport() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean confirmLogin(String username, String password,
            CredentialConfirmer confirm) {
        if (confirm.doesAccountExist(username)) {
            if (confirm.isPasswordCorrect(username, password)) {
                user = confirm.getLoggedInUser();
                return true;
            }
        }
        return false;
    }

    /**
     * Gets the begin date.
     *
     * @return The beginDate.
     */
    public static Date getBeginDate() {
        return beginDate;
    }

    /**
     * Sets the begin date.
     *
     * @param startDate The beginDate to set.
     */
    public static void setBeginDate(Date startDate) {
        UserAccountController.beginDate = startDate;
    }

    /**
     * Gets the calendar date object.
     *
     * @return The calendar date object.
     */
    public static Date getTheDate() {
        return theDate;
    }

    /**
     * Sets the calendar date.
     *
     * @param date The calendar date to set for a time period.
     */
    public static void setTheDate(Date date) {
        UserAccountController.theDate = date;
    }

    /**
     * Gets the endDate for a time period.
     *
     * @return The endDate. The end date for a time period.
     */
    public static Date getEndDate() {
        return endDate;
    }

    /**
     * Sets the end date for a time period.
     *
     * @param endDate The endDate to set for a time period.
     */
    public static void setEndDate(Date end) {
        UserAccountController.endDate = end;
    }
}
