package com.model.buckaroos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class defines a database to provide persistence to the application.
 * Class that uses SQLite to store all of the app's information into a database
 *
 * @author Jordan LeRoux
 * @author Gene Hynson
 * @version 1.4
 */
public class DB extends SQLiteOpenHelper {

    private static final String LOGCAT = null;
    private static final String TABLE_CREDENTIALS = "Credentials";
    private static final String KEY_LOGINACCOUNT = "Account";
    private static final String KEY_PASSWORD = "Password";
    private static final String KEY_EMAIL = "Email";
    private static final String TABLE_ACCOUNTS = "Accounts";
    private static final String KEY_USERACCOUNTNAME = "UserAccountName";
    private static final String KEY_ACCOUNTNICKNAME = "AccountNickName";
    private static final String KEY_BALANCE = "Balance";
    private static final String KEY_INTERESTRATE = "InterestRate";
    private static final String KEY_CREATIONDATE = "CreationDate";
    private static final String TABLE_TRANSACTIONS = "Transactions";
    private static final String KEY_AMOUNT = "Amount";
    private static final String KEY_TRANSACTIONTYPE = "TransactionType";
    private static final String KEY_CURRENCYTYPE = "CurrencyType";
    private static final String KEY_CATEGORY = "Category";
    private static final String KEY_TRANSACTIONDATE = "TransactionDate";
    private static final String KEY_TRANSACTIONTIME = "TransactionTime";

    public DB(Context applicationcontext) {
        super(applicationcontext, "app5.db", null, 1);
        Log.d(LOGCAT, "Created");
    }

    @Override
    public void onCreate(SQLiteDatabase database) {
        String query;
        query =
                "CREATE TABLE Credentials ( Account TEXT PRIMARY KEY, Password "
                        + "TEXT NOT NULL, Email TEXT NOT NULL)";
        database.execSQL(query);
        Log.d(LOGCAT, "Credentials Created");
        // Will change Balance INT to Balance MONEY NOT NULL after we create a
        // money class
        // Need to change balnce in account class to BigDecimal
        query =
                "CREATE TABLE Accounts ( Account TEXT, UserAccountName TEXT, "
                        + "Balance DOUBLE NOT NULL, InterestRate DOUBLE, CreationDate "
                        + "DATETIME DEFAULT CURRENT_TIMESTAMP, AccountNickName TEXT, "
                        + "primary KEY (UserAccountName), "
                        + "foreign KEY (Account) REFERENCES Credentials(Account) ON "
                        + "DELETE CASCADE)";
        // Was CreationDate DATETIME DEFAULT CURRENT_TIMESTAMP
        // Consider using DATETIME DEFAULT CURRENT_TIMESTAMP instead of
        // (getDate())
        database.execSQL(query);
        query =
                "CREATE TABLE Transactions ( Account TEXT NOT NULL, UserAccount"
                        + "Name TEXT NOT NULL, Amount DOUBLE NOT NULL, TransactionType"
                        + " NVARCHAR(15) CHECK (TransactionType IN ('Withdrawal', 'Deposit'))"
                        + ", CurrencyType NVARCHAR(30), Category TEXT, TransactionDate "
                        + "TEXT, CreationDate DATETIME DEFAULT CURRENT_TIMESTAMP, "
                        + "TransactionTime TEXT, "
                        + "primary KEY (Amount, Category, CreationDate, Account, "
                        + "UserAccountName, TransactionDate),"
                        + " foreign KEY (Account) REFERENCES Credentials(Account) ON "
                        + "DELETE CASCADE, foreign KEY (UserAccountName) REFERENCES "
                        + "Accounts(UserAccountName) ON DELETE CASCADE)";
        // Was CreationDate DATETIME DEFAULT CURRENT_TIMESTAMP
        // Have to add a check when we decide what currencies we want to allow
        database.execSQL(query);
        Log.d(LOGCAT, "Accounts Created");
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int version_old,
            int current_version) {
        String query;
        query = "DROP TABLE IF EXISTS Accounts";
        database.execSQL(query);
        query = "DROP TABLE IF EXISTS Credentials";
        database.execSQL(query);
        query = "DROP TABLE IF EXISTS Transactions";
        database.execSQL(query);
        onCreate(database);
    }

    /**
     * Adds a user to the database
     *
     * @param user The user to be added
     */
    public void addUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LOGINACCOUNT, user.getAccountName());
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        db.insert(TABLE_CREDENTIALS, null, values);
        System.out.println("just added");
        db.close();
    }

    /**
     * Adds an account to the database. Note: This is an account of a user, it
     * is different than the account the user logs in with
     *
     * @param account The account to be added
     * @param user The user that the account is associated with
     */
    public void addAccount(Account account, User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_LOGINACCOUNT, user.getAccountName());
        values.put(KEY_USERACCOUNTNAME, account.getName());
        values.put(KEY_BALANCE, account.getBalance());
        values.put(KEY_INTERESTRATE, account.getInterestRate());
        values.put(KEY_ACCOUNTNICKNAME, account.getNickName());
        System.out.println(values);
        db.insert(TABLE_ACCOUNTS, null, values);
        String query =
                "SELECT " + KEY_BALANCE + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + KEY_LOGINACCOUNT + "=" + "'"
                        + user.getAccountName() + "'";
        System.out.println(query);
        Cursor c = db.rawQuery(query, null);
        if (c != null) {
            if (c.moveToFirst()) {
                double balance =
                        c.getDouble(c.getColumnIndex(KEY_BALANCE));
                System.out.println("Balance: " + balance);
            }
        }
        db.close();
        System.out.println("just added account");
    }

    /**
     * Adds a transaction to the database.
     *
     * @param account The account the transaction is associated with
     * @param loginAccountName The account name that the user uses to log in
     *            with
     * @param amount The amount of the transaction
     * @param transactionType Specifies if the transaction is a withdrawal or a
     *            deposit
     * @param currencyType The type of currency used in the transaction
     * @param category The expense or income category
     */
    public void
            addTransaction(Account account, String loginAccountName,
                    double amount, String transactionType,
                    String currencyType, String category,
                    String transactionDate, String transactionTime) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        if (transactionType.equalsIgnoreCase("Withdrawal")) {
            amount = -amount;
        }
        values.put(KEY_LOGINACCOUNT, loginAccountName);
        values.put(KEY_USERACCOUNTNAME, account.getName());
        values.put(KEY_AMOUNT, amount);
        values.put(KEY_TRANSACTIONTYPE, transactionType);
        values.put(KEY_CURRENCYTYPE, currencyType);
        values.put(KEY_CATEGORY, category);
        values.put(KEY_TRANSACTIONDATE, transactionDate);
        values.put(KEY_TRANSACTIONTIME, transactionTime);
        db.insert(TABLE_TRANSACTIONS, null, values);
        db.close();
        updateAccount(account, loginAccountName, amount);
        System.out.println("just added transaction");
    }

    /**
     * Removes a transaction from the user's account and updates the account's
     * balance to reflect the deletion.
     *
     * @param account The account of the transaction that is being deleted
     * @param user The user whose account is being updated
     * @param transaction The transaction that is to be deleted
     */
    public void removeTransaction(Account account, User user,
            AccountTransaction transaction) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery =
                "SELECT " + KEY_AMOUNT + ", " + KEY_TRANSACTIONTYPE
                        + " FROM " + TABLE_TRANSACTIONS + " WHERE "
                        + KEY_LOGINACCOUNT + " = '"
                        + user.getAccountName() + "' AND "
                        + KEY_USERACCOUNTNAME + " = '" + account.getName()
                        + "' AND " + KEY_AMOUNT + " = '"
                        + transaction.getAmount() + "' AND "
                        + KEY_CATEGORY + " = '"
                        + transaction.getCategory() + "' AND "
                        + KEY_CREATIONDATE + " = '"
                        + transaction.getCreationDate() + "' AND "
                        + KEY_TRANSACTIONDATE + " = '"
                        + transaction.getDate() + "'";
        Cursor c = db.rawQuery(selectQuery, null);
        String query =
                "DELETE FROM " + TABLE_TRANSACTIONS + " WHERE "
                        + KEY_LOGINACCOUNT + " = '"
                        + user.getAccountName() + "' AND "
                        + KEY_USERACCOUNTNAME + " = '" + account.getName()
                        + "' AND " + KEY_AMOUNT + " = '"
                        + transaction.getAmount() + "' AND "
                        + KEY_CATEGORY + " = '"
                        + transaction.getCategory() + "' AND "
                        + KEY_CREATIONDATE + " = '"
                        + transaction.getCreationDate() + "' AND "
                        + KEY_TRANSACTIONDATE + " = '"
                        + transaction.getDate() + "'";
        db.rawQuery(query, null);
        if (c != null) {
            if (c.moveToFirst()) {
                double amount = c.getDouble(c.getColumnIndex(KEY_AMOUNT));
                String type =
                        c.getString(c.getColumnIndex(KEY_TRANSACTIONTYPE));
                // If the transaction is a deposit and you delete it, you will
                // subtract it from the balance, so you update with a negative
                // amount
                if (type.equalsIgnoreCase("Deposit")) {
                    amount = -amount;
                }
                updateAccount(account, user.getAccountName(), amount);
            }
        }
        db.close();
    }

    /**
     * Retrieves an account from the database.
     *
     * @param accountName The name of the account to be retrieved
     * @param user The user associated with the account
     * @return The Account if it exists, null otherwise
     */
    public Account getAccount(String accountName, User user) {
        Account returnAccount = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =
                "SELECT  * FROM " + TABLE_ACCOUNTS + " WHERE "
                        + KEY_LOGINACCOUNT + " = '"
                        + user.getAccountName() + "' AND "
                        + KEY_USERACCOUNTNAME + " = '" + accountName + "'";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String accName =
                        c.getString(c.getColumnIndex(KEY_USERACCOUNTNAME));
                String nickName =
                        c.getString(c.getColumnIndex(KEY_ACCOUNTNICKNAME));
                double balance =
                        c.getDouble((c.getColumnIndex(KEY_BALANCE)));
                double interest =
                        c.getDouble(c.getColumnIndex(KEY_INTERESTRATE));
                returnAccount =
                        new Account(accName, nickName, balance, interest,
                                user);
            } while (c.moveToNext());
        }
        db.close();
        return returnAccount;
    }

    /**
     * Retrieves the first account associated with the user.
     *
     * @param user The user whose account is to be retrieved
     * @return The first account associated with the user, null if the user has
     *         no accounts
     */
    public Account getFirstAccount(User user) {
        Account returnAccount = null;
        ArrayList<Account> accounts = getAllAccounts(user);
        if (!accounts.isEmpty()) {
            returnAccount = accounts.get(0);
        }
        return returnAccount;
    }

    /**
     * Updates an account with a new balance.
     *
     * @param account The account to update
     * @param loginAccountName The name of the user's login account
     * @param amount The amount to add/subtract from the balance
     */
    public void updateAccount(Account account, String loginAccountName,
            double amount) {
        SQLiteDatabase db = this.getWritableDatabase();
        String selectQuery =
                "SELECT " + KEY_BALANCE + " FROM " + TABLE_ACCOUNTS
                        + " WHERE " + KEY_LOGINACCOUNT + "=" + "'"
                        + loginAccountName + "' AND "
                        + KEY_USERACCOUNTNAME + "= '" + account.getName()
                        + "'";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c != null) {
            if (c.moveToFirst()) {
                double balance =
                        c.getDouble(c.getColumnIndex(KEY_BALANCE));
                balance += amount;
                String updateQuery =
                        "Update " + TABLE_ACCOUNTS + " SET balance = "
                                + balance + " WHERE " + KEY_LOGINACCOUNT
                                + "=" + "'" + loginAccountName + "' AND "
                                + KEY_USERACCOUNTNAME + "= '"
                                + account.getName() + "'";
                System.out.println(balance);
                db.execSQL(updateQuery);
            }
        }
        db.close();
    }

    /**
     * Retrieves all accounts from the database.
     *
     * @return An ArrayList containing all the accounts in the database
     */
    public ArrayList<Account> getAllAccounts(User user) {
        ArrayList<Account> accList = new ArrayList<Account>();
        String selectQuery =
                "SELECT  * FROM " + TABLE_ACCOUNTS + " WHERE "
                        + KEY_LOGINACCOUNT + "= '" + user.getAccountName()
                        + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String acc =
                        c.getString(c.getColumnIndex(KEY_LOGINACCOUNT));
                String accName =
                        c.getString(c.getColumnIndex(KEY_USERACCOUNTNAME));
                String nickName =
                        c.getString(c.getColumnIndex(KEY_ACCOUNTNICKNAME));
                double balance =
                        c.getDouble((c.getColumnIndex(KEY_BALANCE)));
                double interest =
                        c.getDouble(c.getColumnIndex(KEY_INTERESTRATE));
                Account account =
                        new Account(accName, nickName, balance, interest,
                                user);
                if (acc.equalsIgnoreCase(user.getAccountName())) {
                    accList.add(account);
                }
            } while (c.moveToNext());
        }
        db.close();
        return accList;
    }

    /**
     * Retrieves a user from the database.
     *
     * @param accountName The name of the account the user uses to log in with
     * @return The user associated with the accountName provided
     */
    public User getUser(String accountName) {
        User returnUser = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String selectQuery =
                "SELECT  * FROM " + TABLE_CREDENTIALS + " WHERE "
                        + KEY_LOGINACCOUNT + " = '" + accountName + "'";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String accName =
                        c.getString(c.getColumnIndex(KEY_LOGINACCOUNT));
                String pw = c.getString(c.getColumnIndex(KEY_PASSWORD));
                String email = c.getString((c.getColumnIndex(KEY_EMAIL)));
                returnUser = new User(accName, pw, email);
            } while (c.moveToNext());
        }
        db.close();
        return returnUser;
    }

    /**
     * Retrieves all users from the database.
     *
     * @return An ArrayList containing all the users in the database
     */
    private ArrayList<User> getAllUsers() {
        ArrayList<User> userList = new ArrayList<User>();
        String selectQuery = "SELECT  * FROM " + TABLE_CREDENTIALS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String acc =
                        c.getString(c.getColumnIndex(KEY_LOGINACCOUNT));
                String pw = c.getString(c.getColumnIndex(KEY_PASSWORD));
                String em = c.getString(c.getColumnIndex(KEY_EMAIL));
                User user = new User(acc, pw, em);
                userList.add(user);
            } while (c.moveToNext());
        }
        db.close();
        return userList;
    }

    /**
     * Updates an existing user's password and email. Pass in a
     *
     * @param user The user containing the login account name to update and the
     *            new email and/or password.
     * @return The number of rows affected by the update
     */
    public int updateUser(User user) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, user.getPassword());
        values.put(KEY_EMAIL, user.getEmail());
        return db.update(TABLE_CREDENTIALS, values, KEY_LOGINACCOUNT
                + " = ?", new String[] { user.getAccountName() });
    }

    /**
     * Checks to see if the password is correct for the accountName provided.
     *
     * @param accountName The name of the account whose password is being
     *            checked
     * @param password The password to check
     * @return True if the password is correct, false otherwise
     */
    public boolean isPasswordCorrect(String accountName, String password) {
        ArrayList<User> userList = getAllUsers();
        boolean isPasswordCorrect = false;
        for (User user : userList) {
            if (accountName != null) {
                if (accountName.equals(user.getAccountName())
                        && password.equals(user.getPassword())) {
                    isPasswordCorrect = true;
                }
            }
        }
        return isPasswordCorrect;
    }

    /**
     * Returns a list of the transactions for an account.
     *
     * @param account The account containing the transactions
     * @param user The user of the account
     * @return an arraylist with all of the transactions for one account
     */
    public List<AccountTransaction> getAllAccountTransactions(
            Account account, User user) {
        List<AccountTransaction> transactionList =
                new ArrayList<AccountTransaction>();
        System.out.println("pre query");
        String selectQuery =
                "SELECT "
                        + KEY_AMOUNT
                        + ", "
                        + KEY_TRANSACTIONTYPE
                        + ", "
                        + KEY_CURRENCYTYPE
                        + ", "
                        + KEY_CATEGORY
                        + ", "
                        + KEY_TRANSACTIONDATE
                        + ", "
                        + KEY_TRANSACTIONTIME
                        + ", "
                        + "(strftime('%s', CreationDate) * 1000) AS CreationDate FROM "
                        + TABLE_TRANSACTIONS + " WHERE "
                        + KEY_LOGINACCOUNT + " = '"
                        + user.getAccountName() + "' AND "
                        + KEY_USERACCOUNTNAME + " = '" + account.getName()
                        + "'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        System.out.println("executed query");
        if (c.moveToFirst()) {
            do {
                double amount =
                        c.getDouble((c.getColumnIndex(KEY_AMOUNT)));
                String transactionType =
                        c.getString((c.getColumnIndex(KEY_TRANSACTIONTYPE)));
                String currencyType =
                        c.getString((c.getColumnIndex(KEY_CURRENCYTYPE)));
                String category =
                        c.getString((c.getColumnIndex(KEY_CATEGORY)));
                long millis =
                        c.getLong(c
                                .getColumnIndexOrThrow(KEY_CREATIONDATE));
                Date date = new Date(millis);
                String transactionDate =
                        c.getString((c.getColumnIndex(KEY_TRANSACTIONDATE)));
                String transactionTime =
                        c.getString((c.getColumnIndex(KEY_TRANSACTIONTIME)));
                AccountTransaction transaction =
                        new AccountTransaction(amount, currencyType,
                                transactionType, category, date,
                                transactionDate, transactionTime);
                transactionList.add(transaction);

            } while (c.moveToNext());
        }
        db.close();
        return transactionList;
    }

    /**
     * Called by getIncomeSourceInfo and getSpendingCategoryInfo. Performs a
     * select query to retrieve the appropriate information needed to produce
     * spending category and income source reports. Takes in a transactionType
     * to know which type of report to retrieve info for. Returns null if a
     * database error occurs
     */
    private Map<String, Double> getTransactionCategoryInfo(
            String username, String accountName, String startDate,
            String endDate, String transType) {
        // The date passed in needs to be formatted as YYYY/MM/DD
        Map<String, Double> categoryMap = new HashMap<String, Double>();
        String selectQuery =
                "SELECT Category, SUM(Amount) AS amount FROM "
                        + "Transactions WHERE Account = '" + username
                        + "' AND " + "UserAccountName = '" + accountName
                        + "' AND " + "TransactionType = '" + transType
                        + "' AND TransactionDate > '" + startDate
                        + "' AND TransactionDate < '" + endDate
                        + "' GROUP " + "BY Category";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            do {
                String category = c.getString(1);
                double totalSpending = c.getDouble(2);
                categoryMap.put(category, totalSpending);
            } while (c.moveToNext());
        }
        return categoryMap;
    }

    /**
     * Retrieves the spending categories and the amount spent in those
     * categories associated with a username and account between a startDate and
     * endDate.
     *
     * @param username The username with which the spending categories are to be
     *            retrieved from
     * @param accountName The accountName with which the spending categories are
     *            to be retrieved from
     * @param startDate The beginning of the date range to retrieve transactions
     *            from
     * @param endDate The end of the date range to retrieve transactions from
     * @return A hashmap containing the spending categories as keys and the
     *         amount spent as values or null if a database error occurs
     */
    public Map<String, Double> getSpendingCategoryInfo(String username,
            String accountName, String startDate, String endDate) {
        return getTransactionCategoryInfo(username, accountName,
                startDate, endDate, "Withdrawal");
    }

    /**
     * Retrieves the income sources and the amount earned in those categories
     * associated with a username and account between a startDate and endDate.
     *
     * @param username The username with which the income sources are to be
     *            retrieved from
     * @param accountName The accountName with which the income sources are to
     *            be retrieved from
     * @param startDate The beginning of the date range to retrieve transactions
     *            from
     * @param endDate The end of the date range to retrieve transactions from
     * @return A hashmap containing the income sources as keys and the amount
     *         earned as values or null if a database error occurs
     */
    public Map<String, Double> getIncomeSourceInfo(String username,
            String accountName, String startDate, String endDate) {
        return getTransactionCategoryInfo(username, accountName,
                startDate, endDate, "Deposit");
    }
}
