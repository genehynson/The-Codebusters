package com.password.buckaroos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
//Here is a comment


/**
 * Writes a file that stores the information about accounts and passwords that
 * have been created
 * 
 * @author Jordan LeRoux
 * @version 1.0
 */
public class AppPropertyWriter {

	private Properties appProps = new Properties();
	private Properties emailProps = new Properties();
	private String registrationError;
	private static File passwordFile = new File("/userCredentials/appProperties.txt");
    private static File emailFile = new File("/userCredentials/emailProperties.txt");
    private static File credentialFolder = new File("/userCredentials/");

	/**
	 * Constructs an AppPropertyWriter.
	 * It gets the old properties that have been written to the application
	 * previously and is ready to store more accounts and passwords
	 */
	public AppPropertyWriter() {
		writeDefaultProperties();
	}

	public void storeAccountEmailAndPassword(String accountName, String 
			password, String email) {
		getOldProperties(appProps, passwordFile);
		if (doesAccountExist(accountName)) {
			registrationError = "The account name has been taken";
			System.out.println(registrationError);
		} else {
			storeAccountAndPassword(accountName,password);
			storeAccountAndEmail(accountName,email);
		}
	}

	/*
	 * Stores a new account and password that has been registered.
	 * If the account name has been taken, the user is informed
	 * 
	 * @param accountName The account name to be created
	 * @param password The password that corresponds with the new account
	 */
	private void storeAccountAndPassword(String accountName, String password) {
		//Could add some other constraints such as length and requiring an uppercase letter, etc.
		if (accountName != null && password != null) {
			getOldProperties(appProps, passwordFile);
			MessageDigest md;
			try {
				md = MessageDigest.getInstance("MD5");
				md.update(password.getBytes());
				byte[] digest = md.digest();
				StringBuffer sb = new StringBuffer();
				for (byte b : digest) {
					sb.append(Integer.toHexString((int) (b & 0xff)));
				}
				appProps.setProperty(accountName, sb.toString());
				try {
					FileOutputStream out = new FileOutputStream(passwordFile);
//					FileOutputStream out = openFileOutput(passwordFile, Context.MODE_PRIVATE);
					appProps.store(out, null);
					out.close();
				} catch (FileNotFoundException e) {
					System.out.println("password file not found");
				} catch (IOException e) {
					System.out.println("IO Exception");
				}
			} catch (NoSuchAlgorithmException e1) {
				//DO Nothing
			}
		}
	}

	private void storeAccountAndEmail(String accountName, String email) {
		if (accountName != null && email != null) {
			getOldProperties(appProps, passwordFile);
			getOldProperties(emailProps, emailFile);
			emailProps.setProperty(accountName, email);
			try {
				FileOutputStream out = new FileOutputStream(emailFile);
				emailProps.store(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("email file not found");
			} catch (IOException e) {
				System.out.println("IO Exception");
			}
		}
	}

	/*
	 * Returns true if the account name has been stored in the application's
	 * properties file, false otherwise
	 * 
	 * @param accountName The account name whose existence is in question
	 */
	private boolean doesAccountExist(String accountName) {
		return appProps.containsKey(accountName);
	}

	/*
	 * Loads the file that stores the application properties and adds them to
	 * the appProps instance that is being maintained
	 * 
	 * @param applicationProps The Properties instance that is being updated
	 * with the old properties
	 */
	private static void getOldProperties(Properties applicationProps,
			File fileName) {
		Properties defaultProps = new Properties();
		FileInputStream in;
		try {
			in = new FileInputStream(fileName);
			defaultProps.load(in);
			in.close();
		} catch (FileNotFoundException e1) {
			System.out.println("No old properties");
		} catch (InvalidPropertiesFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (Enumeration<Object> e = defaultProps.keys(); e.hasMoreElements();) {
			String key = (String) e.nextElement();
			String value = defaultProps.getProperty(key);
			applicationProps.setProperty(key, value);
		}
	}

	/*
	 * Creates the admin account and password and adds it to the properties to
	 * be stored
	 */
	private static void writeDefaultProperties() {
		//Write admin's default email null in the emailproperties file
		if (!(credentialFolder.exists())) {
			credentialFolder.mkdirs();
			try {
				passwordFile.createNewFile();
				emailFile.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Properties defaultProps = new Properties();
		getOldProperties(defaultProps, passwordFile);
		Properties defaultEmailProps = new Properties();
		getOldProperties(defaultEmailProps, emailFile);
		defaultEmailProps.setProperty("admin", "");
		String original = "pass123";
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(original.getBytes());
			byte[] digest = md.digest();
			StringBuffer sb = new StringBuffer();
			for (byte b : digest) {
				sb.append(Integer.toHexString((int) (b & 0xff)));
			}
			defaultProps.setProperty("admin", sb.toString());
			try {
				FileOutputStream out = new FileOutputStream(passwordFile);
				defaultProps.store(out, null);
				out.close();
				out = new FileOutputStream(emailFile);
				defaultEmailProps.store(out, null);
				out.close();
			} catch (FileNotFoundException e) {
				System.out.println("File Not Found");
			} catch (IOException e) {
				System.out.println("IO Exception");
			}
		} catch (NoSuchAlgorithmException e1) {
			//DO Nothing
		}
	}

}

