package com.password.buckaroos;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DB extends SQLiteOpenHelper {

	private static final String LOGCAT = null;
	private static final String TABLE_ACCOUNTS = "Accounts";
	private static final String KEY_ACCOUNT = "Account";
	private static final String KEY_PASSWORD = "Password";
	private static final String KEY_EMAIL = "Email";

	public DB(Context applicationcontext) {
		super(applicationcontext, "userCredentials.db", null, 1);
		Log.d(LOGCAT,"Created");
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		String query;
		query = "CREATE TABLE Accounts ( Account TEXT PRIMARY KEY, Password "
				+ "TEXT NOT NULL, Email TEXT NOT NULL)";
		database.execSQL(query);
		Log.d(LOGCAT,"Accounts Created");
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, int version_old, int current_version) {
		String query;
		query = "DROP TABLE IF EXISTS Accounts";
		database.execSQL(query);
		onCreate(database);
	}

	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_ACCOUNT, user.get_accountName());
		values.put(KEY_PASSWORD, user.get_password());
		values.put(KEY_EMAIL, user.get_email());
		db.insert(TABLE_ACCOUNTS, null, values);
		System.out.println("just added");
		db.close();
	}
	
	public void addAccount(Account account, User user) {
		user.addAccount(account);
		System.out.println("just added account");
	}
	
	public Account getAccount(User user, String accountName) {
		ArrayList<Account> accounts = user.getAccounts();
		for(Account account : accounts) {
			if (account.getName().equals(accountName)) {
				return account;
			}
		}
		return null;
	}

	public User getUser(String accountName) {
		//		SQLiteDatabase db = this.getReadableDatabase();
		//		User returnUser = null;
		//		Cursor c = db.rawQuery("SELECT " + KEY_ACCOUNT + ", " + KEY_PASSWORD 
		//				+ "," + KEY_EMAIL + " FROM " + " " + TABLE_ACCOUNTS, null);
		//		if (c != null ) { //TODO: Something messed up here??
		//			if  (c.moveToFirst()) {
		//				String acc = c.getString(c.getColumnIndex(KEY_ACCOUNT));
		//				String pw = c.getString(c.getColumnIndex(KEY_PASSWORD));
		//				String em = c.getString(c.getColumnIndex(KEY_EMAIL));
		//				returnUser = new User(acc, pw, em);
		//			}
		//		}	
		//		c.close();
		User returnUser = null;
		if (accountName != null) {
			ArrayList<User> users = getAllUsers();
			for (User user : users) {
				if (user != null) {
					String acc = user.get_accountName();
					if (accountName.equals(acc)) {
						returnUser = new User(user.get_accountName(), 
								user.get_password(), user.get_email());
					}
				}
			}
		}
		return returnUser;
	}

	private ArrayList<User> getAllUsers() {
		ArrayList<User> userList = new ArrayList<User>();
		String selectQuery = "SELECT  * FROM " + TABLE_ACCOUNTS;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor c = db.rawQuery(selectQuery, null);
		if (c.moveToFirst()) {
			do {
				String acc = c.getString(c.getColumnIndex(KEY_ACCOUNT));
				String pw = c.getString(c.getColumnIndex(KEY_PASSWORD));
				String em = c.getString(c.getColumnIndex(KEY_EMAIL));
				User user = new User(acc, pw, em);
				userList.add(user);
			} while (c.moveToNext());
		}
		return userList;
	}

	public int updateUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_PASSWORD, user.get_password());
		values.put(KEY_EMAIL, user.get_email());
		return db.update(TABLE_ACCOUNTS, values, KEY_ACCOUNT + " = ?",
				new String[] { user.get_accountName() });
	}

	public boolean isPasswordCorrect(String accountName, String password) {
		ArrayList<User> userList = getAllUsers();
		boolean isPasswordCorrect = false;
		for (User user : userList) {
			if (accountName != null) {
				if (accountName.equals(user.get_accountName()) &&
						password.equals(user.get_password())) {
					isPasswordCorrect = true;
				}
			}
		}
		//		if (accountName != null) {
		//			User userToCheck = getUser(accountName);
		//			String pw = userToCheck.get_password();
		//			if (pw.equals(password)) {
		////			PasswordHash hasher = new PasswordHash();
		////			if (pw.equals(hasher.hashPassword(password))) {
		//				isPasswordCorrect = true;
		//			}
		//		}
		return isPasswordCorrect;
	}

	//	public void insertUser(HashMap<String, String> queryValues) {
	//        SQLiteDatabase database = this.getWritableDatabase();
	//        ContentValues values = new ContentValues();
	//        values.put("AccountName", queryValues.get("AccountName"));
	//        database.insert("Accounts", null, values);
	//        database.close();
	//    }

}