package com.buckaroos.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buckaroos.client.DBConnectionAsync;
import com.buckaroos.server.Account;
import com.buckaroos.server.AccountTransaction;
import com.buckaroos.server.User;
import com.buckaroos.utility.AppPropertyWriter;
import com.buckaroos.utility.CredentialConfirmer;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;

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

    private static User user;
    private static Account currentAccount;
    private static Date beginDate;
    private static Date theDate;
    private static Date endDate;
    private static DBConnectionAsync db;
    private AsyncCallback<User> callbackUser;
    private AsyncCallback<Account> callbackAccount;
    private AsyncCallback<AccountTransaction> callbackTransaction;
    private AsyncCallback<List<AccountTransaction>> callbackListTransactions;
    private AsyncCallback<List<Account>> callbackListAccounts;
    private AsyncCallback<HashMap<String, Double>> callbackHashMap;

    private Account resultAccount;
    private User resultUser;
    private AccountTransaction resultTransaction;
    private List<AccountTransaction> resultListTransactions;
    private List<Account> resultListAccounts;
    private HashMap<String, Double> resultHashMap;
    
    /**
     * Gets user/DB after login from CredientialConfirmer in Login activity.
     * 
     * @param user
     */
    @SuppressWarnings("static-access")
    public UserAccountController(User user) {
        this.user = user;
    	callbackUser = new CallbackHandler<User>();
    	callbackAccount = new CallbackHandler<Account>();
    	callbackTransaction = new CallbackHandler<AccountTransaction>();
    }

    public UserAccountController() {
    	callbackUser = new CallbackHandler<User>();
    	callbackAccount = new CallbackHandler<Account>();
    	callbackTransaction = new CallbackHandler<AccountTransaction>();
    }
    
    public UserAccountController(DBConnectionAsync db) {
    	this.db = db;
    	callbackUser = new CallbackHandler<User>();
    	callbackAccount = new CallbackHandler<Account>();
    	callbackTransaction = new CallbackHandler<AccountTransaction>();
    }

    @Override
    public Account getUserAccount(String accountName) {
    	db.getAccount(user.getUsername(), accountName, callbackAccount);
    	if (resultAccount != null) {
    		return resultAccount;
    	} else {
    		return null;
    	}
    }

    @Override
    public void addAccount(String accountName, String accountNickName,
            double amount, double interestRate) {
        currentAccount =
                new Account(user.getUsername(), accountName, accountNickName, amount, interestRate);
        db.addAccount(user.getUsername(), currentAccount, callbackAccount);
    }

    @Override
    public void addWithdrawal(double amount, String currencyType,
            String category, Date date) {
        String dateString = convertDateToString(date);
        String timeString = convertTimeToString(date);
        db.addTransaction(currentAccount.getName(), user.getUsername(), amount,
                "Withdrawal", currencyType, category, dateString, timeString, callbackTransaction);
    }

    @Override
    public void addDeposit(double amount, String currencyType, String category,
            Date date) {
        String dateString = convertDateToString(date);
        String timeString = convertTimeToString(date);
        db.addTransaction(user.getUsername(), currentAccount.getName(), amount,
                "Deposit", currencyType, category, dateString, timeString, callbackTransaction);
    }

    @Override
    public String convertTimeToString(Date date) {
    	String pattern = "HH:mm"; /*your pattern here*/ 
    	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
    	DateTimeFormat df = new DateTimeFormat(pattern, info) {};  // <= trick here
        return df.format(date);
    }

    @Override
    public String convertDateToString(Date date) {
    	String pattern = "yyyyMMdd"; /*your pattern here*/ 
    	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
    	DateTimeFormat df = new DateTimeFormat(pattern, info) {};  // <= trick here
        return df.format(date);
    }

    @Override
    public Date convertStringToDate(String dateString) {
    	String pattern = "yyyyMMdd"; /*your pattern here*/ 
    	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
    	DateTimeFormat df = new DateTimeFormat(pattern, info) {};  // <= trick here
        return df.parse(dateString);
    }

    @Override
    public Account getCurrentAccount() {
        return currentAccount;
    }

    @Override
    public boolean hasAccount() {
    	db.getAllAccounts(user.getUsername(), callbackListAccounts);
    	if (resultListAccounts != null && resultListAccounts.size() != 0) {
    		return true;
    	}
        return false;
    }

    @Override
    public Account getFirstUserAccount() {
    	db.getAllAccounts(user.getUsername(), callbackListAccounts);
    	
        return resultListAccounts.get(0);
    }

    @Override
    public void setCurrentAccount(Account account) {
        currentAccount = account;
    }

    @Override
    public DBConnectionAsync getDB() {
        return db;
    }

    @Override
    public void addLoginAccount(String name, String password, String email) {
        AppPropertyWriter k = new AppPropertyWriter();
        user = k.storeAccount(name, password, email);
    }

    @Override
    public User getLoginAccount(String username) {
    	db.getUser(username, callbackUser);
    	if (resultUser != null) {
    		return resultUser;
    	}
    	return null;
    }

    @Override
    public User getCurrentUser() {
        return user;
    }

    @Override
    public List<Account> getAllUserAccounts() {
        db.getAllAccounts(user.getUsername(), callbackListAccounts);
        if (resultListAccounts != null) {
        	return resultListAccounts;
        }
        return null;
    }

    @Override
    public List<AccountTransaction> getAllAccountTransactions() {
        db.getAllTransactions(user.getUsername(),currentAccount.getName(), callbackListTransactions);
        if (resultListTransactions != null) {
        	return resultListTransactions;
        }
        return null;
    }

    @Override
    public boolean doesLoginAccountExist(String accountName) {
        db.getUser(accountName, callbackUser);
        if (resultUser != null) {
        	return true;
        } 
        return false;
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
                    if ((beginDate.before(theDate) && endDate.after(theDate))
                            || beginDate.equals(theDate)
                            || endDate.equals(theDate)) {
                        System.out.println("found date");
                        if (transaction.getType().equals("Withdrawal")) {
                            if (!categoryTotals.containsKey(transaction
                                    .getCategory())) {
                                categoryTotals.put(transaction.getCategory(),
                                        transaction.getAmount());
                                categoryNames.add(transaction.getCategory());
                            } else {
                                categoryTotals.put(
                                        transaction.getCategory(),
                                        categoryTotals.get(transaction
                                                .getCategory())
                                                + transaction.getAmount());
                            }
                            totalSpending =
                                    transaction.getAmount() + totalSpending;
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
                    if ((beginDate.before(theDate) && endDate.after(theDate))
                            || beginDate.equals(theDate)
                            || endDate.equals(theDate)) {
                        if (transaction.getType().equals("Withdrawal")) {
                            if (!categoryNames.contains(transaction
                                    .getCategory())) {
                                categoryNames.add(transaction.getCategory());
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

    //
    // @Override
    // public Map<String, Double> getIncomeTransactionsTotalInDate() {
    // List<AccountTransaction> importedTransactions =
    // getAllAccountTransactions();
    // List<Account> allAccounts = getAllUserAccounts();
    // Account actualCurrentAccount = getCurrentAccount();
    //
    // List<String> categoryNames = new ArrayList<String>();
    // Map<String, Double> categoryTotals = new HashMap<String, Double>();
    //
    // String date;
    // double totalIncome = 0;
    //
    // for (Account account : allAccounts) {
    // setCurrentAccount(account);
    // importedTransactions = getAllAccountTransactions();
    // for (AccountTransaction transaction : importedTransactions) {
    // date = transaction.getDate();
    // if (!date.equals("00/00/0")) {
    // theDate = convertStringToDate(date);
    // System.out.println("before " + beginDate);
    // System.out.println(theDate.toString());
    // System.out.println("after " + endDate);
    // if ((beginDate.before(theDate) && endDate.after(theDate))
    // || beginDate.equals(theDate)
    // || endDate.equals(theDate)) {
    // System.out.println("found date");
    // if (transaction.getType().equals("Deposit")) {
    // if (!categoryTotals.containsKey(transaction
    // .getCategory())) {
    // categoryTotals.put(transaction.getCategory(),
    // transaction.getAmount());
    // categoryNames.add(transaction.getCategory());
    // } else {
    // categoryTotals.put(
    // transaction.getCategory(),
    // categoryTotals.get(transaction
    // .getCategory())
    // + transaction.getAmount());
    // }
    // totalIncome = transaction.getAmount() + totalIncome;
    // }
    // }
    // }
    // }
    // }
    // categoryTotals.put("Total", totalIncome);
    // categoryNames.add("Total");
    // setCurrentAccount(actualCurrentAccount);
    // return categoryTotals;
    // }
    //
    // @Override
    // public List<String> getIncomeTransactionSourceInDate() {
    // List<String> categoryNames = new ArrayList<String>();
    //
    // List<AccountTransaction> importedTransactions =
    // getAllAccountTransactions();
    // List<Account> allAccounts = getAllUserAccounts();
    // Account actualCurrentAccount = getCurrentAccount();
    //
    // String date;
    //
    // for (Account account : allAccounts) {
    // setCurrentAccount(account);
    // importedTransactions = getAllAccountTransactions();
    // for (AccountTransaction transaction : importedTransactions) {
    // date = transaction.getDate();
    // if (!date.equals("00/00/0")) {
    // theDate = convertStringToDate(date);
    // if ((beginDate.before(theDate) && endDate.after(theDate))
    // || beginDate.equals(theDate)
    // || endDate.equals(theDate)) {
    // if (transaction.getType().equals("Deposit")) {
    // if (!categoryNames.contains(transaction
    // .getCategory())) {
    // categoryNames.add(transaction.getCategory());
    // }
    // }
    // }
    // }
    // }
    // }
    // categoryNames.add("Total");
    // setCurrentAccount(actualCurrentAccount);
    // return categoryNames;
    // }
    //
    // @Override
    // public Map<String, Double> getTransactionHistoryTotalInDate() {
    // List<AccountTransaction> importedTransactions =
    // getAllAccountTransactions();
    // List<Account> allAccounts = getAllUserAccounts();
    // Account actualCurrentAccount = getCurrentAccount();
    //
    // List<String> categoryNames = new ArrayList<String>();
    // Map<String, Double> categoryTotals = new HashMap<String, Double>();
    //
    // String date;
    // double totalIncome = 0;
    //
    // for (Account account : allAccounts) {
    // setCurrentAccount(account);
    // importedTransactions = getAllAccountTransactions();
    // for (AccountTransaction transaction : importedTransactions) {
    // date = transaction.getDate();
    // if (!date.equals("00/00/0")) {
    // theDate = convertStringToDate(date);
    // System.out.println("before " + beginDate);
    // System.out.println(theDate.toString());
    // System.out.println("after " + endDate);
    // if ((beginDate.before(theDate) && endDate.after(theDate))
    // || beginDate.equals(theDate)
    // || endDate.equals(theDate)) {
    // System.out.println("found date");
    // if (transaction.getType().equals("Deposit")) {
    // if (!categoryTotals.containsKey(transaction
    // .getCategory())) {
    // categoryTotals.put(transaction.getCategory(),
    // transaction.getAmount());
    // categoryNames.add(transaction.getCategory());
    // } else {
    // categoryTotals.put(
    // transaction.getCategory(),
    // categoryTotals.get(transaction
    // .getCategory())
    // + transaction.getAmount());
    // }
    // totalIncome = transaction.getAmount() + totalIncome;
    // }
    // }
    // }
    // }
    // }
    // categoryTotals.put("Total", totalIncome);
    // categoryNames.add("Total");
    // setCurrentAccount(actualCurrentAccount);
    // return categoryTotals;
    // }
    //
    // @Override
    // public List<String> getTransactionHistoryNamesInDate() {
    // List<String> categoryNames = new ArrayList<String>();
    //
    // List<AccountTransaction> importedTransactions =
    // getAllAccountTransactions();
    // List<Account> allAccounts = getAllUserAccounts();
    // Account actualCurrentAccount = getCurrentAccount();
    //
    // String date;
    //
    // for (Account account : allAccounts) {
    // setCurrentAccount(account);
    // importedTransactions = getAllAccountTransactions();
    // for (AccountTransaction transaction : importedTransactions) {
    // date = transaction.getDate();
    // if (!date.equals("00/00/0")) {
    // theDate = convertStringToDate(date);
    // if ((beginDate.before(theDate) && endDate.after(theDate))
    // || beginDate.equals(theDate)
    // || endDate.equals(theDate)) {
    // if (transaction.getType().equals("Deposit")) {
    // if (!categoryNames.contains(transaction
    // .getCategory())) {
    // categoryNames.add(transaction.getCategory());
    // }
    // }
    // }
    // }
    // }
    // }
    // categoryNames.add("Total");
    // setCurrentAccount(actualCurrentAccount);
    // return categoryNames;
    // }

    @Override
    public Map<String, Double> generateSpendingCategoryReport() {
        db.getSpendingCategoryInfo(user.getUsername(),
                currentAccount.getName(), beginDate.toString(),
                endDate.toString(), callbackHashMap);
        if (resultHashMap != null) {
        	return resultHashMap;
        }
        return null;
    }

    @Override
    public Map<String, Double> generateIncomeSourceReport() {
        // TODO Auto-generated method stub
        return null;
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
     * @param beginDate The beginDate to set.
     */
    public static void setBeginDate(Date beginDate) {
        UserAccountController.beginDate = beginDate;
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
     * @param theDate The calendar date to set for a time period.
     */
    public static void setTheDate(Date theDate) {
        UserAccountController.theDate = theDate;
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
    public static void setEndDate(Date endDate) {
        UserAccountController.endDate = endDate;
    }
    
    private class CallbackHandler<T> implements AsyncCallback<T> {

		@Override
		public void onFailure(Throwable caught) {
			Window.alert("Server request failed.");
		}

		@Override
		public void onSuccess(T result) {
			if (result instanceof Account) {
				resultAccount = (Account) result;
			} else if (result instanceof User) {
				resultUser = (User) result;
			} else if (result instanceof AccountTransaction) {
				resultTransaction = (AccountTransaction) result;
			} else if (result instanceof List<?>) {
				if (((List<?>) result).get(0) != null && ((List<?>) result).get(0) instanceof AccountTransaction) {					
					resultListTransactions = (List<AccountTransaction>) result;
				} else if (((List<?>) result).get(0) != null && ((List<?>) result).get(0) instanceof Account) {
					resultListAccounts = (List<Account>) result;
				}
			} else if (result instanceof HashMap) {
				resultHashMap = (HashMap<String, Double>) result;
			}
		}
    	
    }
}
