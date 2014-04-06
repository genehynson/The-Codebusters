package com.utility.buckaroos;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import android.content.Context;

import com.model.buckaroos.DB;
import com.model.buckaroos.User;

/**
 * This class loads credentials stored in a file and has methods that allow for
 * retrieval of emails and provides a way to check if a password is correct or
 * not.
 *
 * @author Jordan LeRoux
 * @version 2.0
 */
public class CredentialConfirmer {

    private DB db;
    private static User currentLoggedInUser;
    private final int toConvert = 0xff;

    /**
     * Constructs a CredentialConfirmer by getting all the keys and values from
     * the properties that have been written to the application.
     *
     * @param ctx The context object.
     */
    public CredentialConfirmer(Context ctx) {
        db = new DB(ctx);
    }

    /**
     * Checks if the account name provided exists.
     *
     * @param accountName The account name whose existence is in question
     * @return true if the account has been registered, false otherwise
     */
    public boolean doesAccountExist(String accountName) {
        return (db.getUser(accountName) != null);
    }

    /**
     * Checks if the password for the account is correct.
     *
     * @param accountName The account whose password is being checked
     * @param aPassword The password to check with the account
     * @return true if the account's password matches the provided password,
     *         false otherwise
     */
    public boolean isPasswordCorrect(String accountName, String aPassword) {
        String thePassword = "";
        StringBuffer sb = null;
        if (doesAccountExist(accountName)) {
            thePassword = db.getUser(accountName).getPassword();
            System.out.println("TheAccount: " + accountName);
            System.out.println("ThePassword: " + thePassword);
        }
        MessageDigest md;
        sb = new StringBuffer();
        sb.append("");
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(aPassword.getBytes());
            byte[] digest = md.digest();
            sb.replace(0, 0, "");
            for (byte b : digest) {
                sb.append(Integer.toHexString((int) (b & toConvert)));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        System.out.println("aPassword: " + sb.toString());
        if (thePassword.equals(sb.toString())) {
            currentLoggedInUser = db.getUser(accountName);
            return true;
        }
        return false;
    }

    /**
     * Retrieves the currently logged in user.
     *
     * @return The current logged in user.
     */
    public User getLoggedInUser() {
        return currentLoggedInUser;
    }

    /**
     * Returns the email associated with the accountName provided.
     *
     * @param accountName The name of the account for which the email is being
     *            received
     * @return The email associated with the account
     */
    public String getEmail(String accountName) {
        return db.getUser(accountName).getEmail();
    }

    /**
     * Gets an instance of the database.
     *
     * @return An instance of the database object.
     */
    public DB getDB() {
        return db;
    }
}
