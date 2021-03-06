package com.buckaroos.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import android.content.Context;

import com.buckaroos.server.User;

/**
 * Writes a file that stores the information about accounts and passwords that
 * have been created
 * 
 * @author Jordan LeRoux
 * @version 1.0
 */
public class AppPropertyWriter {

//    private static DB db;

    /**
     * Constructs an AppPropertyWriter. It gets the old properties that have
     * been written to the application previously and is ready to store more
     * accounts and passwords
     */
    public AppPropertyWriter() {
//        db = new DB();
        writeDefaultProperties();
    }

    /**
     * Stores a new account and password that has been registered. If the
     * account name has been taken, the user is informed
     * 
     * @param accountName The account name to be created
     * @param password The password that corresponds with the new account
     */
    public User storeAccount(String accountName, String password, String email) {
        User newUser = null;
        if (accountName != null && email != null && password != null) {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
                md.update(password.getBytes());
                byte[] digest = md.digest();
                StringBuffer sb = new StringBuffer();
                for (byte b : digest) {
                    sb.append(Integer.toHexString((int) (b & 0xff)));
                }
                newUser = new User(accountName, sb.toString(), email);
//                db.addUser(newUser);
            } catch (NoSuchAlgorithmException e1) {
                // Do Nothing
            }
        }
        return newUser;
    }

    /**
     * Returns true if the account name has been stored in the application's
     * properties file, false otherwise
     * 
     * @param accountName The account name whose existence is in question
     */
    public boolean doesAccountExist(String accountName) {
//        if (db.getUser(accountName) != null) {
//            return true;
//        }
        return false;
    }

    /**
     * Creates the admin account and password and adds it to the properties to
     * be stored
     */
    private static void writeDefaultProperties() {
        String adminUserName = "admin";
        String adminPassword = "pass123";
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("MD5");
            md.update(adminPassword.getBytes());
            byte[] digest = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(Integer.toHexString((int) (b & 0xff)));
            }
//            db.addUser(new User(adminUserName, sb.toString(), " "));
        } catch (NoSuchAlgorithmException e) {
            // Do nothing
        }

    }

}
