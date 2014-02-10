package com.password.buckaroos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;
//Here is a comment.

/**
 * This class loads credentials stored in a file and has methods that allow for 
 * retrieval of emails and provides a way to check if a password is correct or 
 * not
 * 
 * @author Jordan LeRoux
 * @version 1.0
 */
public class CredentialConfirmer {

	private String thePassword = "";
	private String theEmail = "";
	private Properties applicationProps = new Properties();
	private Properties emailProps = new Properties();
	private static File passwordFile = new File("/userCredentials/appProperties.txt");
    private static File emailFile = new File("/userCredentials/emailProperties.txt");

	/**
	 * Constructs a CredentialConfirmer by getting all the keys and values from
	 * the properties that have been written to the application
	 */
	public CredentialConfirmer() {
		FileInputStream in;
		try {
			in = new FileInputStream(passwordFile);
			applicationProps.load(in);
			in.close();
			in = new FileInputStream(emailFile);
			emailProps.load(in);
			in.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("IO Exception");
		}

	}
	
	/**
	 * Checks if the account name provided exists
	 * 
	 * @param accountName The account name whose existence is in question
	 * @return true if the account has been registered, false otherwise
	 */
	public boolean doesAccountExist(String accountName) {
		return applicationProps.containsKey(accountName);
	}

	/**
	 * Checks if the password for the account is correct
	 * 
	 * @param accountName The account whose password is being checked
	 * @param aPassword The password to check with the account
	 * @return true if the account's password matches the provided password, 
	 * false otherwise
	 */
	public boolean isPasswordCorrect(String accountName, String aPassword) {
		if (doesAccountExist(accountName)) {
			thePassword = (String) applicationProps.get(accountName);
		}
		MessageDigest md;
		StringBuffer sb = new StringBuffer();
		sb.append("");
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(aPassword.getBytes());
			byte[] digest = md.digest();
			sb.replace(0, 0, "");
			for (byte b : digest) {
				sb.append(Integer.toHexString((int) (b & 0xff)));
			}
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return this.thePassword.equals(sb.toString());
	}

	/**
	 * Returns the email associated with the accountName provided
	 * 
	 * @param accountName The name of the account for which the email is being 
	 * received
	 * @return The email associated with the account
	 */
	public String getEmail(String accountName) {
		if (doesAccountExist(accountName)) {
			theEmail = (String) emailProps.get(accountName);
		} else {
			theEmail = null;
		}
		return theEmail;
	}
}

