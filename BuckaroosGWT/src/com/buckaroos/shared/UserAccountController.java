package com.buckaroos.shared;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.buckaroos.server.Account;
import com.buckaroos.server.AccountTransaction;
import com.buckaroos.server.DataMap;
import com.buckaroos.server.User;
import com.buckaroos.utility.AppPropertyWriter;
import com.buckaroos.utility.CredentialConfirmer;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DefaultDateTimeFormatInfo;

/**
 * Controller between activities and User/Account
 * 
 * @author Gene
 * @version 1.0
 * 
 */
public class UserAccountController implements ControllerInterface {

    private static User user;
//    private static DB db;
    private static Account currentAccount;
    private static Date beginDate;
    private static Date theDate;
    private static Date endDate;
    private static DataMap map;

    /**
     * Gets user/DB after login from CredientialConfirmer in Login activity
     * 
     * @param user
     */
    @SuppressWarnings("static-access")
    public UserAccountController(User user) {
//        db = new DB();
        this.user = user;
    }

    public UserAccountController() {
//        db = new DB();
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getUserAccount(java.lang.String)
	 */
    @Override
	public Account getUserAccount(String accountName) {
//        return db.getAccount(accountName, user);
    	return null;
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
//            db.addAccount(currentAccount, user);
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
//        db.addTransaction(currentAccount, user.getAccountName(), amount,
//                "Withdrawal", currencyType, category, dateString, timeString);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addDeposit(double, java.lang.String, java.lang.String, java.util.Date)
	 */
    @Override
	public void addDeposit(double amount, String currencyType, String category,
            Date date) {
        String dateString = convertDateToString(date);
        String timeString = convertTimeToString(date);
//        db.addTransaction(currentAccount, user.getAccountName(), amount,
//                "Deposit", currencyType, category, dateString, timeString);
    }

   
    /**
     * Converts integer time to strings
     * @param hour
     * @param minute
     * @return
     */
    public String convertTimeToString(Date date) {
    	String pattern = "HH:mm"; /*your pattern here*/ 
    	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
    	DateTimeFormat df = new DateTimeFormat(pattern, info) {};  // <= trick here
    	return df.format(date);
    }
    
    /**
     * Converts date into string
     * @param date
     * @return
     */
    public String convertDateToString(Date date) {
    	String pattern = "yyyyMMdd"; /*your pattern here*/ 
    	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
    	DateTimeFormat df = new DateTimeFormat(pattern, info) {};  // <= trick here
    	return df.format(date);
    }
    
    public Date convertStringToDate(String dateString) {
    	String pattern = "yyyyMMdd"; /*your pattern here*/ 
    	DefaultDateTimeFormatInfo info = new DefaultDateTimeFormatInfo();
    	DateTimeFormat df = new DateTimeFormat(pattern, info) {};  // <= trick here
    	Date date = new Date();
    	date = df.parse(dateString);
    	return date;
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
//        return db.getFirstAccount(user) != null;
    	return false;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getFirstUserAccount()
	 */
    @Override
	public Account getFirstUserAccount() {
//        return db.getFirstAccount(user);
    	return null;
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
//    @Override
//	public DB getDB() {
//        return db;
//    	return null;
//    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#addLoginAccount(java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
	public void addLoginAccount(String name, String password, String email) {
        AppPropertyWriter k = new AppPropertyWriter();
        user = k.storeAccount(name, password, email);
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getLoginAccount(java.lang.String)
	 */
    @Override
	public User getLoginAccount(String username) {
//        return db.getUser(username);
    	return null;
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
//        return db.getAllAccounts(user);
    	return null;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#getAllAccountTransactions()
	 */
    @Override
	public List<AccountTransaction> getAllAccountTransactions() {
//        return db.getAllAccountTransactions(currentAccount, user);
    	return null;
    }

    /* (non-Javadoc)
	 * @see com.controller.buckaroos.ControllerInterface#doesLoginAccountExist(java.lang.String)
	 */
    @Override
	public boolean doesLoginAccountExist(String accountName) {
//        return db.getUser(accountName) != null;\
    	return false;
    }
    
    public Map<String, Double> getTransactionsInDate() {
        List<AccountTransaction> importedTransactions = getAllAccountTransactions();
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
    	
        List<AccountTransaction> importedTransactions = getAllAccountTransactions();
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
                            if (!categoryNames.contains(transaction.getCategory())) {
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
    
    public boolean confirmLogin(String username, String password, CredentialConfirmer confirm) {
        if (confirm.doesAccountExist(username)) {
            if (confirm.isPasswordCorrect(username, password)) {
            	user = confirm.getLoggedInUser();
            	return true;
            }
        }
        return false;
    }

	/**
	 * @return the beginDate
	 */
	public static Date getBeginDate() {
		return beginDate;
	}

	/**
	 * @param beginDate the beginDate to set
	 */
	public static void setBeginDate(Date beginDate) {
		UserAccountController.beginDate = beginDate;
	}

	/**
	 * @return the theDate
	 */
	public static Date getTheDate() {
		return theDate;
	}

	/**
	 * @param theDate the theDate to set
	 */
	public static void setTheDate(Date theDate) {
		UserAccountController.theDate = theDate;
	}

	/**
	 * @return the endDate
	 */
	public static Date getEndDate() {
		return endDate;
	}

	/**
	 * @param endDate the endDate to set
	 */
	public static void setEndDate(Date endDate) {
		UserAccountController.endDate = endDate;
	}
	
	public void addObject(String name, Object o) {
		map.add(name, o);
	}
	
	public Object getObject(String name) {
		return map.getObject(name);
	}
	
	public List<String> getAllObjectNames() {
		return map.getAllObjectNames();
	}
}
